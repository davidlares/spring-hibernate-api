package com.davidlares.davidAPI.service;

import java.util.List;

import com.davidlares.davidAPI.model.SocialMedia;
import com.davidlares.davidAPI.model.TeacherSocialMedia;

public interface SocialMediaService {

	void saveSocialMedia(SocialMedia socialMedia);
	void deleteSocialMediaById(Long id);
	void updateSocialMedia(SocialMedia socialMedia);
	List<SocialMedia> findAllSocialMedias();
	SocialMedia findByID(Long idSocialMedia);
	SocialMedia findByName(String name);
	TeacherSocialMedia findSocialMediaByIDAndName(Long idSocialmedia, String nickname);
	TeacherSocialMedia findSocialMediaByIdTeacherAndIdSocialMedia(Long idTeacher, Long idSocialMedia);
}
