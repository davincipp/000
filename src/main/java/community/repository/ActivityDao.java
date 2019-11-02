package community.repository;

import java.sql.Clob;
import java.util.List;

import community.model.Activity;
import community.model.ActivityReply;
import community.model.MemberUploadImage;
import community.model.Reply;
import register.model.MemberBean;

public interface ActivityDao {

	public Integer saveActivity(Activity activity);
	public Activity queryActivity(Integer id);
	public Clob getContentText(Integer id);
	public boolean deleteActivity(Activity activity);
	public Activity preModifyActivity(MemberBean mb, Integer id);
	public Integer saveActivityReply(ActivityReply reply);
	public Integer deleteReply(Activity d);
	public List<Reply> getReplyList(Activity activity);
	public Clob getReplyContentText(Integer id);
	public MemberBean queryMember(Integer id);
	public boolean updateActivity(Activity activity);
	public Integer saveMemberUploadImage(MemberUploadImage mui);
	public MemberUploadImage queryMemberUploadImage(Integer id);
	public List<Activity> getActivityList(String sort, Integer page);
	public Long getActivityCount();
//	public Integer savePoint(Point point);
//	public Long getTotalPoint(Integer activityId, Integer replyId);
//	public Long getTotalPoint(Integer activityId);
//	public Point queryPointRecord(MemberBean member, Integer activityId, Integer replyId);
//	public Point queryPointRecord(MemberBean member, Integer activityId);
//	public Boolean deletePoint(Point point);
//	public Integer deleteActivityPoint(Activity activity);



}
