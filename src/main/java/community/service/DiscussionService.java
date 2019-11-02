package community.service;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.List;

import community.model.Discussion;
import community.model.MemberUploadImage;
import community.model.Point;
import community.model.Reply;
import register.model.MemberBean;

public interface DiscussionService {
	
	Integer saveDiscussion(Discussion discussion);
	Discussion queryDiscussion(Integer id);
	Clob getContentText(Integer id);
	boolean deleteDiscussion(MemberBean mb, Integer id);
	Discussion preUpdateDiscussion(MemberBean mb, Integer id);
	Integer saveReply(Reply reply);
	List<Reply> getReplyList(Discussion discussion);
	Clob getReplyContentText(Integer id);
	MemberBean queryMember(Integer id);
	Integer saveMemberUploadImage(MemberUploadImage mui);
	MemberUploadImage queryMemberUploadImage(Integer id);
	List<Discussion> getDiscussionList(String sort, Integer page);
	Integer getTotalPages();
	boolean updateDiscussion(MemberBean mb, Integer intDid, String title, Clob content, Blob coverImage,
			String coverImageName, Timestamp deadline);
	Integer savePoint(Point point);
	Integer getPointType(MemberBean member, Integer discussionId, Integer replyId);
	Boolean preModifyPointRecord(MemberBean member, Integer discussionId, Integer replyId);
	Long getTotalPoint(Integer discussionId, Integer replyId);
	
}
