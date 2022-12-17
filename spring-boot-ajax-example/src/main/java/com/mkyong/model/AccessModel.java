package com.mkyong.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String Lookup;
    @Getter
    @Setter
    private String country;
    @Getter
    @Setter
    private int employees;
    @Getter
    @Setter
    private int ARank;
    @Getter
    @Setter
    private int QRank;
    @Getter
    @Setter
    private int MJRank;
    @Getter
    @Setter
    private int MJTLDRank;
    @Getter
    @Setter
    private int refSN;
    @Getter
    @Setter
    private int refIP;
    @Getter
    @Setter
    private int Followers;
    @Getter
    @Setter
    private int lastIndexed;
    @Getter
    @Setter
    private int CDimensions;

    public AccessModel(String lookup, String country, int employees, int ARank, int QRank, int MJRank, int MJTLDRank, int refSN, int refIP, int followers, int lastIndexed, int CDimensions) {
        Lookup = lookup;
        this.country = country;
        this.employees = employees;
        this.ARank = ARank;
        this.QRank = QRank;
        this.MJRank = MJRank;
        this.MJTLDRank = MJTLDRank;
        this.refSN = refSN;
        this.refIP = refIP;
        Followers = followers;
        this.lastIndexed = lastIndexed;
        this.CDimensions = CDimensions;
    }
}
