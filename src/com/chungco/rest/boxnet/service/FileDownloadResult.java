package com.chungco.rest.boxnet.service;

public class FileDownloadResult extends AbstractBoxResult {

    public final static String SUCCESSFUL_DOWNLOAD = "succesful_download";

    @Override
    public Boolean success() {

        return SUCCESSFUL_DOWNLOAD.equals(getStatus());
    }

}
