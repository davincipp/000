package community.service;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.List;

import community.model.Activity;
import community.model.ActivityReply;
import community.model.MemberUploadImage;
import community.model.Reply;
import register.model.MemberBean;

public interface ActivityService {
	
	Integer saveActivity(Activity activity);
	Activity queryActivity(Integer id);
	Clob getContentText(Integer id);
	boolean deleteActivity(MemberBean mb, Integer id);
	Activity preUpdateActivity(MemberBean mb, Integer id);
	Integer saveReply(ActivityReply reply);
	List<Reply> getReplyList(Activity activity);
	Clob getReplyContentText(Integer id);
	MemberBean queryMember(Integer id);
	Integer saveMemberUploadImage(MemberUploadImage mui);
	MemberUploadImage queryMemberUploadImage(Integer id);
	List<Activity> getActivityList(String sort, Integer page);
	Integer getTotalPages();
	boolean updateActivity(MemberBean mb, Integer intDid, String title, Clob content, Blob coverImage,
			String coverImageName, Timestamp deadline, Integer intPrice, String location);
//	Integer savePoint(Point point);
//	Integer getPointType(MemberBean member, Integer activityId, Integer replyId);
//	Boolean preModifyPointRecord(MemberBean member, Integer activityId, Integer replyId);
//	Long getTotalPoint(Integer activityId, Integer replyId);

}
