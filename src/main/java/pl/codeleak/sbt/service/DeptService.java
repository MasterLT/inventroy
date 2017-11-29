package pl.codeleak.sbt.service;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codeleak.sbt.dingding.DingMessageUtiles;
import pl.codeleak.sbt.dto.*;
import pl.codeleak.sbt.entity.*;
import pl.codeleak.sbt.enums.AssetEnum;
import pl.codeleak.sbt.enums.AssetStateEnum;
import pl.codeleak.sbt.enums.InventoryEnum;
import pl.codeleak.sbt.enums.RoleEnum;
import pl.codeleak.sbt.repository.*;
import pl.codeleak.sbt.utils.DateUtiles;
import pl.codeleak.sbt.utils.ExcelUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/9.
 */
@Service
@Transactional
public class DeptService {
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
    InventoryService inventoryService;
    @Autowired
    OaCompanyuserRepository oaCompanyuserRepository;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(DeptService.class);

    public List<SysUser> getUsersByDeptId(String deptKey) {
        return sysUserRepository.findByDepartmentKey(deptKey);
    }

    public Object getAssetDate(Integer start, Integer length, String extra_search, String deptKey) {
        Department dept = getDeptByDeptkey(deptKey);
        String[] params = extra_search.split("@");
        String assetcode = null;
        String userName = params[0];
        if (params.length == 1)
            assetcode = "%";
        else assetcode = params[1];
        int page = start == null ? 0 : start / length;
        DatatablesViewPage<AssetDTO> data = new DatatablesViewPage<AssetDTO>();
        Pageable pageable = new PageRequest(page, length);
        Page<Asset> list = null;
        if ("0".equals(userName))
            list = assetRepository.findByAssetcodeLikeAndDeptnameAndState(assetcode, dept.getDepartmentValue(), AssetStateEnum.USING.getCode(), pageable);
        else
            list = assetRepository.findByPlaceAndAssetcodeLikeAndDeptnameAndState(userName, assetcode, dept.getDepartmentValue(), AssetStateEnum.USING.getCode(), pageable);
        List<AssetDTO> inventoryVO = Lists.newArrayList();
        if (list != null)
            for (Asset p : list) {
                AssetDTO dto = new AssetDTO();
                BeanUtils.copyProperties(p, dto);
                dto.setInventorystate(AssetEnum.getNameByType(p.getInventorystate()));
                inventoryVO.add(dto);
            }
        data.setAaData(inventoryVO);
        data.setiTotalDisplayRecords((int) list.getTotalElements());
        data.setiTotalRecords((int) list.getTotalElements());
        return data;
    }

    private Department getDeptByDeptkey(String key) {
        List<Department> depts = departmentRepository.findByDepartmentKey(key);
        if (depts.size() > 0)
            return depts.get(0);
        else return null;
    }

    public Object detail(String assetcode) {
        List<Asset> assets = assetRepository.findByAssetcode(assetcode);
        Asset asset = assets.get(0);
        AssetDTO dto = new AssetDTO();
        BeanUtils.copyProperties(asset, dto);
        dto.setState(AssetStateEnum.getNameByType(asset.getState()));
        dto.setInventorystate(AssetEnum.getNameByType(asset.getInventorystate()));
        if (asset.getInventorydate() != null)
            dto.setInventorydate(DateUtiles.formatDateTime(asset.getInventorydate()));
        if (asset.getBuydate() != null)
            dto.setBuydate(DateUtiles.formatDateTime(asset.getBuydate()));
        return dto;
    }

    public Object getInventoryDate(Integer start, Integer length, String extra_search, String deptKey) {
        Department dept = getDeptByDeptkey(deptKey);
        DatatablesViewPage<InventoryHistoryVO> data = new DatatablesViewPage<InventoryHistoryVO>();
        String[] params = extra_search.split("@");
        Integer state = Integer.valueOf(params[1]);
        Integer inventoryId = Integer.valueOf(params[0]);
        String assetcode = null;
        if (inventoryId == 0) {
            data.setiTotalRecords(0);
            data.setiTotalDisplayRecords(0);
            data.setAaData(Lists.newArrayList());
            return data;
        }
        if (params.length != 3)
            assetcode = "%";
        else assetcode = "%" + params[2];
        int page = start == null ? 0 : start / length;
        Pageable pageable = new PageRequest(page, length, Sort.Direction.DESC, "id");
        Page<InventoryHistory> list = null;
        if (state == 2)
            list = inventoryHistoryRepository.findByInventoryIdAndDeptnameAndAssetcodeLike(inventoryId, dept.getDepartmentValue(), assetcode, pageable);
        else
            list = inventoryHistoryRepository.findByInventoryIdAndDeptnameAndAssetcodeLikeAndInventorystate(inventoryId, dept.getDepartmentValue(), assetcode, state, pageable);
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

    public Object getSeeDate(Integer start, Integer length, String deptKey, Integer state) {
        Department dept = getDeptByDeptkey(deptKey);
        return inventoryService.getSeeDate(start, length, dept.getId(), state);
    }

    public Object noInventory(Integer inventoryId, String assetcode, String remark) {
        InventoryHistory inventoryHistory = getInventoryHistory(inventoryId, assetcode);
        inventoryHistory.setInventoryRemark(remark);
        inventoryHistory.setInventorystate(AssetEnum.NOINVENTORY.getCode());
        em.merge(inventoryHistory);

        Asset asset = getAssets(assetcode);
        asset.setInventoryRemark(remark);
        asset.setInventorystate(AssetEnum.NOINVENTORY.getCode());
        em.merge(asset);

//        Inventory invent = inventoryRepository.getOne(inventoryId);
//        invent.setNoNum(invent.getNoNum() + 1);
//        if (invent.getAssetNum() == invent.getNoNum() + invent.getCompleteNum()) {
//            invent.setState(InventoryEnum.FINISH.getCode());
//            invent.setEndTime(new Date());
//        }
//        em.merge(invent);
        return 1;
    }

    private Asset getAssets(String assetcode) {
        List<Asset> assets = assetRepository.findByAssetcode(assetcode);
        if (assets.size() > 0)
            return assets.get(0);
        else return null;
    }

    private InventoryHistory getInventoryHistory(Integer inventoryId, String assetcode) {
        List<InventoryHistory> inventry = inventoryHistoryRepository.findByInventoryIdAndAssetcodeLike(inventoryId, assetcode);
        if (inventry.size() > 0)
            return inventry.get(0);
        else return null;
    }

    public Object moveInventory(Integer inventoryId, String assetcode, String user) {
        Asset asset = getAssets(assetcode);
        asset.setInventoryUser(user);
        assetRepository.save(asset);
        InventoryHistory inventry = getInventoryHistory(inventoryId, assetcode);
        inventry.setInventoryUser(user);
        inventoryHistoryRepository.save(inventry);
        List<OaCompanyuser> oaCompanyUsers = oaCompanyuserRepository.findByLastnameAndSupname(user, asset.getDeptname());
        if (oaCompanyUsers.size() > 0) {
            DingMessageUtiles.send(oaCompanyUsers.get(0).getWorkcode());
            logger.info("发送资产转移通知:" + oaCompanyUsers.get(0).getLastname());
        }
        return 1;
    }

    public Object inventoryFinish(Integer id) {
        Inventory inventory = inventoryRepository.getOne(id);
        Long unFinish = inventoryHistoryRepository.countByInventoryIdAndState(id, AssetEnum.INVENTORY.getCode());
        if (unFinish != null && unFinish == 0) {
            inventory.setState(InventoryEnum.FINISH.getCode());
            inventoryRepository.save(inventory);
            return 1;
        } else {
            return 0;
        }
    }

    public void outExl(Integer id, HttpServletResponse response) {
        List<InventoryHistory> historys = inventoryHistoryRepository.findByInventoryIdAndAssetcodeLike(id, "%");
        JSONArray ja = new JSONArray();
        int index = 0;
        for (InventoryHistory h : historys) {
            AssetExlDTO dto = new AssetExlDTO();
            dto.setIndex(++index);
            dto.setInventorystate(AssetEnum.getNameByType(h.getInventorystate()));
            BeanUtils.copyProperties(h, dto);
            BeanUtils.copyProperties(assetRepository.findByAssetcode(h.getAssetcode()).get(0), dto);
            ja.add(dto);
        }
        Map<String, String> map = Maps.newLinkedHashMap();
        map.put("index", "序号");
        map.put("assetcode", "资产编码");
        map.put("name", "资产名称");
        map.put("assetsortname", "资产型号");
        map.put("numbers", "数量");
        map.put("place", "使用人");
        map.put("detailedlocation", "存放地址");
        map.put("inventorystate", "盘点结果");
        map.put("inventoryRemark", "备注");
        Inventory inventory = inventoryRepository.getOne(id);
        Department dept = departmentRepository.findOne(inventory.getDepartmentId());
        ExcelUtil.downloadExcelFile(dept.getDepartmentValue() + "部门固定资产盘点表", map, ja, response, DateUtiles.formatDate(inventory.getStartTime()));
    }

    public Object noInventoryBach(Integer[] ids, String remark) {
        for (Integer id : ids) {
            InventoryHistory inventoryHistory = inventoryHistoryRepository.getOne(id);
            if (inventoryHistory.getInventorystate() != AssetEnum.INVENTORY.getCode())
                continue;
            inventoryHistory.setInventoryRemark(remark);
            inventoryHistory.setInventorystate(AssetEnum.NOINVENTORY.getCode());
            em.merge(inventoryHistory);

            Asset asset = getAssets(inventoryHistory.getAssetcode());
            asset.setInventoryRemark(remark);
            asset.setInventorystate(AssetEnum.NOINVENTORY.getCode());
            em.merge(asset);
        }
        return 1;
    }

    public Object moveInventoryBach(Integer[] ids, String user) {
        String dept = "";
        for (Integer id : ids) {
            InventoryHistory inventry = inventoryHistoryRepository.getOne(id);
            if (inventry.getInventorystate() != AssetEnum.INVENTORY.getCode())
                continue;
            dept = inventry.getDeptname();
            inventry.setInventoryUser(user);
            inventoryHistoryRepository.save(inventry);
            Asset asset = getAssets(inventry.getAssetcode());
            asset.setInventoryUser(user);
            assetRepository.save(asset);
        }
        List<OaCompanyuser> oaCompanyUsers = oaCompanyuserRepository.findByLastnameAndSupname(user, dept);
        if (oaCompanyUsers.size() > 0) {
            DingMessageUtiles.send(oaCompanyUsers.get(0).getWorkcode());
            logger.info("发送资产转移通知:" + oaCompanyUsers.get(0).getLastname());
        }
        return 1;
    }
}
