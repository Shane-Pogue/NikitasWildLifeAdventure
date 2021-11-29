package com.Nikitas.WildlifeAdventure.Controllers;

import com.Nikitas.WildlifeAdventure.Domain.Image;
import com.Nikitas.WildlifeAdventure.Domain.ImageType;
import com.Nikitas.WildlifeAdventure.Repositories.ImageRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("image")
public class ImageController {

    private final ImageRepository imageRepository;

    ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @GetMapping({"/{type}"})
    public String getType(Model model, @PathVariable String type) {

        if (type == null || type.isEmpty()) {
            model.addAttribute("page", "fragments/index");
            return "index";
        }

        model.addAttribute("type_id", imageRepository.findByType(ImageType.valueOf(type.toUpperCase())));
        model.addAttribute("page", "fragments/image");

        return "index";
    }

    @GetMapping({"/get/{id}"})
    public void showImage(@PathVariable Long id, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");

        Image image = imageRepository.findById(id).get();

        InputStream is = new ByteArrayInputStream(image.getPicture());
        IOUtils.copy(is, response.getOutputStream());
    }
}
