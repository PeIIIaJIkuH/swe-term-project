package com.hotel.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room_type")
public class RoomType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private Integer size;

	@NotNull
	private Integer capacity;

	@NotNull
	private Integer price;

	@ElementCollection
	@CollectionTable(name = "room_type_feature",
			joinColumns = @JoinColumn(name = "room_type_id", referencedColumnName = "id"))
	@Column(nullable = false)
	private Set<RoomTypeFeature> features = new HashSet<>();

	public RoomType() {
	}

	public RoomType(@NotNull String name, @NotNull Integer size, @NotNull Integer capacity, @NotNull Integer price) {
		this.name = name;
		this.size = size;
		this.capacity = capacity;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Set<RoomTypeFeature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<RoomTypeFeature> features) {
		this.features = features;
	}
}
