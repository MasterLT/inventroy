package pl.codeleak.sbt.service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.codeleak.sbt.dingding.DingMessageUtiles;
import pl.codeleak.sbt.dto.*;
import pl.codeleak.sbt.entity.*;
import pl.codeleak.sbt.enums.AssetEnum;
import pl.codeleak.sbt.enums.AssetStateEnum;
import pl.codeleak.sbt.enums.RoleEnum;
import pl.codeleak.sbt.repository.*;
import pl.codeleak.sbt.utils.DateUtiles;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/9.
 */
@Repository
@Transactional
public class InventoryService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    SysUserRepository sysUserRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AssetRepository assetRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    InventoryHistoryRepository inventoryHistoryRepository;
    @Autowired
    OaCompanyuserRepository oaCompanyuserRepository;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(InventoryService.class);


    public List<Department> getAllDept() {
        return departmentRepository.findByParentDepartmentkey(null);
    }


    public DatatablesViewPage<InventoryVO> getData(Integer start, Integer length, Integer dept) {
        int page = start == null ? 0 : start / length;
        DatatablesViewPage<InventoryVO> data = new DatatablesViewPage<InventoryVO>();
        Pageable pageable = new PageRequest(page, length);
        Page<Department> list = null;
        if (dept == 0)
            list = departmentRepository.findByParentDepartmentkey(null, pageable);
        else
            list = departmentRepository.findById(dept, pageable);
        List<InventoryVO> inventoryVO = Lists.newArrayList();
        if (list != null)
            for (Department p : list) {
                InventoryVO dto = new InventoryVO();
                dto.setId(p.getId());
                dto.setDeptName(p.getDepartmentValue());
                dto.setDeptNum(assetRepository.countAssetNumByDeptIdAndState(p.getDepartmentValue(), AssetStateEnum.USING.getCode()));
                dto.setLastTime(DateUtiles.formatDateTime(inventoryRepository.getlastInventoryTimeByDeptId(p.getId())));
                List<SysUser> users = sysUserRepository.getAdminByDepartmentKey(p.getDepartmentKey(), RoleEnum.DEPTADMIN.getCode());

                if (users.size() > 0)
                    dto.setDeptUser(users.get(users.size() - 1).getUserName());
                else dto.setDeptUser("-");
                inventoryVO.add(dto);
            }
        data.setAaData(inventoryVO);
        data.setiTotalDisplayRecords((int) list.getTotalElements());
        data.setiTotalRecords((int) list.getTotalElements());
        return data;
    }

    public Object detail(Integer id) {
        Department dept = departmentRepository.findOne(id);
        InventoryVO dto = new InventoryVO();
        dto.setId(dept.getId());
        dto.setDeptName(dept.getDepartmentValue());
        dto.setDeptNum(assetRepository.countAssetNumByDeptIdAndState(dept.getDepartmentValue(), AssetStateEnum.USING.getCode()));
        return dto;
    }

    public Object addInventory(Inventory inventory) {
        inventory.setStartTime(new Date());
        inventory.setState(0);
        inventoryRepository.save(inventory);

        Department department = departmentRepository.findOne(inventory.getDepartmentId());
        List<Asset> assets = assetRepository.findByDeptnameAndState(department.getDepartmentValue(), AssetStateEnum.USING.getCode());
        Set<OaCompanyuser> set = Sets.newHashSet();
        for (Asset asset : assets) {
            //更新资产
            asset.setInventorystate(AssetEnum.INVENTORY.getCode());
            asset.setInventoryUser(asset.getPlace());
            assetRepository.save(asset);
            //插入盘点历史
            InventoryHistory history = new InventoryHistory(asset);
            history.setInventoryId(inventory.getId());
            inventoryHistoryRepository.save(history);
            //发消息
            List<OaCompanyuser> oaCompanyUsers = oaCompanyuserRepository.findByLastnameAndSupname(asset.getPlace(), asset.getDeptname());
            if (oaCompanyUsers.size() > 0)
                set.add(oaCompanyUsers.get(0));
        }
        for(OaCompanyuser oaCompanyUser:set){
            logger.info("发送盘点通知-盘点id："+inventory.getId()+"---"+oaCompanyUser.getLastname());
            DingMessageUtiles.send(oaCompanyUser.getWorkcode());
        }
        return 1;
    }

    public Object getSeeDate(Integer start, Integer length, Integer deptId, Integer state) {
        int page = start == null ? 0 : start / length;
        DatatablesViewPage<InventoryDTO> data = new DatatablesViewPage<InventoryDTO>();
        Pageable pageable = new PageRequest(page, length, Sort.Direction.DESC, "startTime");
        Page<Inventory> list = null;
        if (deptId == 0 && state == -1)
            list = inventoryRepository.findAll(pageable);
        else if (deptId == 0 && state != -1)
            list = inventoryRepository.findByState(state, pageable);
        else if (deptId != 0 && state == -1)
            list = inventoryRepository.findByDepartmentId(deptId, pageable);
        else list = inventoryRepository.findByDepartmentIdAndState(deptId, state, pageable);
        List<InventoryDTO> inventoryVO = Lists.newArrayList();
        if (list != null)
            for (Inventory p : list) {
                InventoryDTO dto = new InventoryDTO(p);
                Department d = departmentRepository.findOne(p.getDepartmentId());
                dto.setDeptName(d.getDepartmentValue());
                List<SysUser> users = sysUserRepository.getAdminByDepartmentKey(d.getDepartmentKey(), RoleEnum.DEPTADMIN.getCode());
                if (users.size() > 0)
                    dto.setDeptUserName(users.get(users.size() - 1).getUserName());
                else dto.setDeptUserName("-");
                dto.setNoNum(inventoryHistoryRepository.countByInventoryIdAndState(p.getId(), AssetEnum.NOINVENTORY.getCode()).intValue());
                dto.setInventoryNum(inventoryHistoryRepository.countByInventoryIdAndState(p.getId(), AssetEnum.INVENTORY.getCode()).intValue());
                dto.setCompleteNum(inventoryHistoryRepository.countByInventoryIdAndState(p.getId(), AssetEnum.FINIDH.getCode()).intValue());
                inventoryVO.add(dto);
            }
        data.setAaData(inventoryVO);
        data.setiTotalDisplayRecords((int) list.getTotalElements());
        data.setiTotalRecords((int) list.getTotalElements());
        return data;
    }

    public Object getAssetData(Integer start, Integer length, String extra_search) {
        DatatablesViewPage<InventoryHistoryVO> data = new DatatablesViewPage<InventoryHistoryVO>();
        String[] params = extra_search.split("@");
        Integer inventoryId = Integer.valueOf(params[0]);
        if (inventoryId == 0) {
            data.setiTotalRecords(0);
            data.setiTotalDisplayRecords(0);
            data.setAaData(Lists.newArrayList());
            return data;
        }
        String assetcode = "%" + params[1];
        Integer state = Integer.valueOf(params[2]);
        int page = start == null ? 0 : start / length;
        Pageable pageable = new PageRequest(page, length);
        Page<InventoryHistory> list = null;
        if (state == 2)
            list = inventoryHistoryRepository.findByInventoryIdAndAssetcodeLike(inventoryId, assetcode, pageable);
        else
            list = inventoryHistoryRepository.findByInventoryIdAndAssetcodeLikeAndInventorystate(inventoryId, assetcode, state, pageable);
        List<InventoryHistoryVO> inventoryVO = Lists.newArrayList();
        if (list != null)
            for (InventoryHistory p : list) {
                InventoryHistoryVO dto = new InventoryHistoryVO();
                BeanUtils.copyProperties(p, dto);
                dto.setInventorystate(AssetEnum.getNameByType(p.getInventorystate()));
                inventoryVO.add(dto);
            }
        data.setAaData(inventoryVO);
        data.setiTotalDisplayRecords((int) list.getTotalElements());
        data.setiTotalRecords((int) list.getTotalElements());
        return data;
    }
}
