package com.Nikitas.WildlifeAdventure.Domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.ArrayUtils;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


@Slf4j
@Data
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;

    @Lob
    private Byte[] picture;

    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    public byte[] getPicture() {
        try {
            Integer i = 0;
            byte[] imageBytes = new byte[picture.length];
            for (byte b : picture) {
                imageBytes[i++] = b;
            }
            return imageBytes;
        }
        catch (Exception e){
            log.error("Error occurred", e);
            e.printStackTrace();
            return null;
        }
    }

    public void setPicture(MultipartFile picture) {
        try {
            Integer i =0;
            Byte[] image = new Byte[picture.getBytes().length];
            for(Byte b : picture.getBytes()){
                image[i++] = b;
            }

            this.picture = image;
        } catch (IOException e) {
            log.error("Error occurred", e);
            e.printStackTrace();
        }
    }
}
