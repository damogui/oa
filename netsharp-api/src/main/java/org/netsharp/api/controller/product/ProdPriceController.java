package org.netsharp.api.controller.product;


import javax.ws.rs.Path;

@Path("/prodprice")
public class ProdPriceController {
//
//    @Autowired
//    private ProdPriceService prodPriceService;
//
//    @RequestMapping(value = "/add")
//    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdPrice prodPrice) {
//        ResponseData data = new ResponseData();
//        prodPriceService.insert(prodPrice);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/delete")
//    public ResponseData delete(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        prodPriceService.delete(pkid);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping("/update")
//    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ProdPrice prodPrice) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        prodPrice.setPkid(pkid);
//        prodPriceService.update(prodPrice);
//        data.setMsg("操作成功");
//        return data;
//    }
//
//    @RequestMapping({"/list"})
//    public ResponseData list(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String page = request.getParameter("page");
//        if (StringUtils.isBlank(page)) {
//            page = "0";
//        }
//        Pager<ProdPrice> pager = prodPriceService.pageByProperties(null, Integer.valueOf(page));
//        data.setData(pager);
//        return data;
//    }
//
//    @RequestMapping("/get")
//    public ResponseData get(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//        String pkidStr = request.getParameter("pkidStr");
//        pkidStr = SecurityUtils.rc4Decrypt(pkidStr);
//        Integer pkid = Integer.valueOf(pkidStr);
//        ProdPrice prodPrice = prodPriceService.findById(pkid);
//        data.setData(prodPrice);
//        return data;
//    }
//    @RequestMapping("/query")
//    public ResponseData queryProdPrice(HttpServletRequest request, HttpServletResponse response) {
//        ResponseData data = new ResponseData();
//
//        String serviceIdStr = request.getParameter("servicedStr");
//        if(StringUtils.isBlank(serviceIdStr))
//        {
//            data.setMsg("服务Id不能为空");
//            data.setCode(400);
//            return data;
//        }
//        serviceIdStr = SecurityUtils.rc4Decrypt(serviceIdStr);
//        Integer serviceId = Integer.valueOf(serviceIdStr);
//
//        String cityIdStr = request.getParameter("cityIdStr");
//        if(StringUtils.isBlank(cityIdStr))
//        {
//            data.setMsg("城市Id不能为空");
//            data.setCode(400);
//            return data;
//        }
//        cityIdStr = SecurityUtils.rc4Decrypt(cityIdStr);
//        Integer cityId = Integer.valueOf(cityIdStr);
//
//        ProdPrice prodPrice = prodPriceService.findOnSaleProdPriceBy(serviceId,cityId);
//        data.setData(prodPrice);
//
//        return data;
//    }

}