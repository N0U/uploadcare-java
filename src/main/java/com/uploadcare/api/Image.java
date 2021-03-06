package com.uploadcare.api;

import com.uploadcare.data.FileData;
import com.uploadcare.data.ImageInfo;

import java.util.Date;

public class Image extends File {
    public enum Orientation {
        /**
         * Image doesn't have orientation data
         */
        UNKONWN(null),
        /**
         * Image oriented as it should be viewed
         */
        NORMAL(1),
        /**
         * Image with normal image mirrored vertically
         */
        FLIPPED(2),
        /**
         * Image rotated on 90 degree clockwise
         */
        NORMAL_90(8),
        /**
         * Image flipped vertically than rotated on 90 degree clockwise
         */
        FLIPPED_90(5),
        /**
         * Image rotated on 180 degree clockwise
         */
        NORMAL_180(3),
        /**
         * Image flipped vertically than rotated on 180 degree clockwise
         */
        FLIPPED_180(4),
        /**
         * Image rotated on 270 degree clockwise
         */
        NORMAL_270(6),
        /**
         * Image flipped vertically than rotated on 270 degree clockwise
         */
        FLIPPED_270(7);
        private final Integer code;
        private Orientation(Integer code) {
            this.code = code;
        }

        public Integer getExifCode() {
            return code;
        }

        public static Orientation getOrientation(Integer code) {
            Orientation[] list = values();
            for(Orientation o : list){
                if(o.getExifCode().equals(code))
                    return o;
            }
            return UNKONWN;
        }
    }

    private final ImageInfo imageInfo;

    Image(Client client, FileData fileData) {
        super(client, fileData);
        this.imageInfo = fileData.imageInfo;
    }

    public int getImageWidth() {
        return imageInfo.width;
    }

    public int getImageHeight() {
        return imageInfo.height;
    }

    public String getFormat() {
        return imageInfo.format;
    }

    public Orientation getOrientation() {
        return Orientation.getOrientation(imageInfo.orientation);
    }

    public boolean hasResolution() {
        return imageInfo.dpi != null && imageInfo.dpi.length == 1;
    }

    public Integer getXResolution() {
        return imageInfo.dpi != null && imageInfo.dpi.length == 1 ? imageInfo.dpi[0] : null;
    }

    public Integer getYResolution() {
        if(imageInfo.dpi == null)
            return null;
        return imageInfo.dpi.length == 2 ? imageInfo.dpi[1] : getXResolution();
    }

    public Date getDatetimeOriginal() {
        return imageInfo.datetimeOriginal;
    }

    public boolean hasGeoLocation() {
        return imageInfo.geoLocation != null;
    }

    public Double getLatitude() {
        return imageInfo.geoLocation != null ? imageInfo.geoLocation.latitude : null;
    }

    public Double getLongitude() {
        return imageInfo.geoLocation != null ? imageInfo.geoLocation.longitude : null;
    }
}
