package com.Nikitas.WildlifeAdventure.Bootstrap;


import com.Nikitas.WildlifeAdventure.Domain.Image;
import com.Nikitas.WildlifeAdventure.Domain.ImageType;
import com.Nikitas.WildlifeAdventure.Repositories.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final ImageRepository imageRepository;

    public Bootstrap(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ArrayList<MultipartFile> readFiles(String directory) {
        File dir = new File(directory);
        ArrayList<MultipartFile> fileData = new ArrayList<>();

        for (File f : dir.listFiles()) {
            if (f.isFile() && !f.getName().contentEquals("dummy.txt")) {
                String name = f.getName();
                String originalName = name;
                String contentType = "text/plain";
                try {
                    fileData.add(new MockMultipartFile(name, originalName, contentType, Files.readAllBytes(f.toPath())));
                } catch (IOException e) {
                    log.error("Error occurred", e);
                    e.printStackTrace();
                }
            }
        }
        return fileData;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Bootstrapping classes...");
        List<MultipartFile> pictures = new ArrayList<>();
        List<Image> images = new ArrayList<>();

        log.info("Loading Birds...");
        pictures = readFiles("src/main/resources/static/Birds");

        for (MultipartFile picture : pictures) {
            Image image = new Image();
            image.setPicture(picture);
            image.setImageType(ImageType.BIRD);
            images.add(image);
        }

        log.info("Loading Fish.....");
        pictures = readFiles("src/main/resources/static/Fish");

        for (MultipartFile picture : pictures) {
            Image image = new Image();
            image.setPicture(picture);
            image.setImageType(ImageType.FISH);
            images.add(image);
        }

        log.info("Loading Flowers.....");
        pictures = readFiles("src/main/resources/static/Flowers");

        for (MultipartFile picture : pictures) {
            Image image = new Image();
            image.setPicture(picture);
            image.setImageType(ImageType.FLOWER);
            images.add(image);
        }

        log.info("Loading General Wildlife.....");
        pictures = readFiles("src/main/resources/static/General wildlife");

        for (MultipartFile picture : pictures) {
            Image image = new Image();
            image.setPicture(picture);
            image.setImageType(ImageType.GENERAL);
            images.add(image);
        }

        log.info("Loading Insects..... and bugs.");
        pictures = readFiles("src/main/resources/static/Insects");

        for (MultipartFile picture : pictures) {
            Image image = new Image();
            image.setPicture(picture);
            image.setImageType(ImageType.INSECT);
            images.add(image);
        }

        log.info("Loading Moons.....");
        pictures = readFiles("src/main/resources/static/Moons");

        for (MultipartFile picture : pictures) {
            Image image = new Image();
            image.setPicture(picture);
            image.setImageType(ImageType.MOON);
            images.add(image);
        }

        log.info("Loading Reptiles.....");
        pictures = readFiles("src/main/resources/static/Reptiles");

        for (MultipartFile picture : pictures) {
            Image image = new Image();
            image.setPicture(picture);
            image.setImageType(ImageType.REPTILE);
            images.add(image);
        }

        log.info("Loading Scenery.....");
        pictures = readFiles("src/main/resources/static/Scenery");

        for (MultipartFile picture : pictures) {
            Image image = new Image();
            image.setPicture(picture);
            image.setImageType(ImageType.SCENERY);
            images.add(image);
        }

        imageRepository.saveAll(images);
        log.info("Successfully loaded picture data.");
    }
}
