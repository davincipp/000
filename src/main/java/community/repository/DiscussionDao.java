package community.repository;

import java.sql.Clob;
import java.util.List;

import community.model.Discussion;
import community.model.MemberUploadImage;
import community.model.Point;
import community.model.Reply;
import register.model.MemberBean;

public interface DiscussionDao {
	
	public Integer saveDiscussion(Discussion discussion);
	public Discussion queryDiscussion(Integer id);
	public Clob getContentText(Integer id);
	public boolean deleteDiscussion(Discussion discussion);
	public Discussion preModifyDiscussion(MemberBean mb, Integer id);
	public Integer saveReply(Reply reply);
	public Integer deleteReply(Discussion d);
	public List<Reply> getReplyList(Discussion discussion);
	public Clob getReplyContentText(Integer id);
	public MemberBean queryMember(Integer id);
	public boolean updateDiscussion(Discussion discussion);
	public Integer saveMemberUploadImage(MemberUploadImage mui);
	public MemberUploadImage queryMemberUploadImage(Integer id);
	public List<Discussion> getDiscussionList(String sort, Integer page);
	public Long getDiscussionCount();
	public Integer savePoint(Point point);
	public Long getTotalPoint(Integer discussionId, Integer replyId);
	public Long getTotalPoint(Integer discussionId);
	public Point queryPointRecord(MemberBean member, Integer discussionId, Integer replyId);
	public Point queryPointRecord(MemberBean member, Integer discussionId);
	public Boolean deletePoint(Point point);
	public Integer deleteDiscussionPoint(Discussion discussion);

}
