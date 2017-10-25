package org.netsharp.api.controller.sys.cms;


import javax.ws.rs.Path;

import org.netsharp.api.controller.sys.cms.base.CmsBaseController;

@Path("/cms/specialactive")
public class SpecialActiveController extends CmsBaseController {
//
//    @Autowired
//    private CmsSpecialActiveService cmsSpecialActiveService;
//
//    /**
//     * 加载列表数据
//     *
//     * @param request
//     * @param response
//     * @return ResponseData
//     */
//    @RequestMapping("/list/data")
//    public ResponseData data(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("status", CMSBase.STATUS_SHOW);
//        List<CmsSpecialActive> list = cmsSpecialActiveService.listByProperties(properties);
//        data.setData(list);
//        return data;
//    }
//
//    /**
//     * 编辑数据
//     *
//     * @param request
//     * @param response
//     * @return ResponseData
//     */
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        Integer pkid = getPkid(request);
//        CmsSpecialActive cmsSpecialActive = cmsSpecialActiveService.findById(pkid);
//        data.setData(cmsSpecialActive);
//        return data;
//    }
//
//    /**
//     * 新增或修改（编辑）操作
//     *
//     * @param request
//     * @param response
//     * @param cmsSpecialActive
//     * @return ResponseData
//     */
//    @RequestMapping("/edit")
//    public ResponseData edit(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CmsSpecialActive cmsSpecialActive, LoginUser user) {
//        ResponseData data = new ResponseData();
//        Integer pkid = getPkid(request);
//        if (pkid == 0) {
//            cmsSpecialActive.setAddUser(user.getUcUser().getPkid());
//            cmsSpecialActive.setUpdUser(user.getUcUser().getPkid());
//            cmsSpecialActive.setStatus(CMSBase.STATUS_INIT);
//            cmsSpecialActiveService.insert(cmsSpecialActive);
//        } else {
//            cmsSpecialActive.setPkid(pkid);
//            cmsSpecialActiveService.update(cmsSpecialActive);
//        }
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    /**
//     * 删除操作
//     *
//     * @param request
//     * @param response
//     * @return ResponseData
//     */
//    @RequestMapping("/delete")
//    public ResponseData delete(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
//        ResponseData data = new ResponseData();
//        Integer pkid = getPkid(request);
//        cmsSpecialActiveService.delete(pkid, user.getUcUser().getPkid());
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    /**
//     * 发布操作跳转
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/publish")
//    public ResponseData publish(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) {
//        ResponseData data = new ResponseData();
//        String ids = StringUtils.trimToEmpty(request.getParameter("ids"));
//        List<Integer> idList = new ArrayList<>();
//        String[] idArray = ids.split(",");
//        for (String idStr : idArray) {
//            int id = NumberUtils.toInt(SecurityUtils.rc4Decrypt(idStr));
//            if (id > 0) {
//                idList.add(id);
//            }
//        }
//        Boolean bool = cmsSpecialActiveService.editPublish(idList, loginUser.getUcUser().getPkid());
//        data.setData(bool);
//        return data;
//    }


}