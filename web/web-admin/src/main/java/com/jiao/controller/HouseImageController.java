package com.jiao.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jiao.base.BaseController;
import com.jiao.entity.HouseImage;
import com.jiao.result.Result;
import com.jiao.service.HouseImageService;
import com.jiao.util.QiniuUtil;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@Controller
@RequestMapping("/houseImage")
public class HouseImageController extends BaseController {

    @Reference
    HouseImageService houseImageService;

    private final static String LIST_ACTION = "redirect:/house/";
    private final static String PAGE_UPLOED_SHOW = "house/upload";

    @GetMapping("/uploadShow/{houseId}/{type}")
    public String uploadShow(ModelMap model, @PathVariable Long houseId, @PathVariable Long type) {
        model.addAttribute("houseId", houseId);
        model.addAttribute("type", type);
        return PAGE_UPLOED_SHOW;
    }

    @PostMapping("/upload/{houseId}/{type}")
    @ResponseBody
    public Result upload(@PathVariable Long houseId, @PathVariable Integer type, @RequestParam(value = "file") MultipartFile[] files) throws Exception {
        if (files.length > 0) {
            for (MultipartFile file : files) {
                String newFileName = UUID.randomUUID().toString();
                // 上传图片
                QiniuUtil.upload2Qiniu(file.getBytes(), newFileName);
                String url = "http://s3pdawexn.hn-bkt.clouddn.com/" + newFileName;

                HouseImage houseImage = new HouseImage();
                houseImage.setHouseId(houseId);
                houseImage.setType(type);
                houseImage.setImageName(file.getOriginalFilename());
                houseImage.setImageUrl(url);
                houseImageService.save(houseImage);
            }
        }
        return Result.ok();
    }

    /**
     * 删除
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/delete/{houseId}/{id}")
    public String delete(ModelMap model, @PathVariable Long houseId, @PathVariable Long id) {
        HouseImage houseImage = houseImageService.getById(id);
        houseImageService.deleteById(id);
        QiniuUtil.deleteFileFromQiniu(houseImage.getImageUrl());
        return LIST_ACTION + houseId;
    }

}
