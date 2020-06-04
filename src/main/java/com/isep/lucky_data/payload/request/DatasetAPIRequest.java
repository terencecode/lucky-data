package com.isep.lucky_data.payload.request;

import io.swagger.annotations.ApiModelProperty;


import javax.validation.constraints.NotNull;

public class DatasetAPIRequest extends DatasetRequest {

    @NotNull
    @ApiModelProperty(value = "The information to get a file by an API")
    private DatasetAPIInfo datasetAPIInfo;

    public DatasetAPIInfo getDatasetAPIInfo() {
        return datasetAPIInfo;
    }

    public void setDatasetAPIInfo(DatasetAPIInfo datasetAPIInfo) {
        this.datasetAPIInfo = datasetAPIInfo;
    }
}
