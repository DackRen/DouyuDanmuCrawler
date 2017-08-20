package com.ylu.beans;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Embedded entity of RoomInfo that Using for Json parse
 * Created by renjunzhou on 2017/8/21.
 */
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
class Data {
    @SerializedName("room_id")
    private String roomId;
    private String online;
    @SerializedName("owner_weight")
    private String ownerWeight;
    @SerializedName("room_status")
    private String roomStatus;
    @SerializedName("fans_num")
    private String fansNum;
}
