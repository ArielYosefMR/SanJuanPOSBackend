package com.sanjuan.pos.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjuan.pos.entity.Color;

public interface ColorRepository extends JpaRepository<Color, Long>{
	
}
