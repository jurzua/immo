package com.urzua.immo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "repo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersistentRepository {
    @Id
    private Long id;
    private String name;
    private String fullName;
    private Integer commits;
    private Integer pulls;
    private String blobsUrl;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    public String getReadmeLink() {
        return this.blobsUrl + "/master/README.md";
    }
}
