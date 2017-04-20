package net.sppan.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sppan.blog.entity.Options;

public interface OptionsRepository extends JpaRepository<Options, Long>{

	Options findByOptionKey(String key);
}
