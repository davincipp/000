package community.repository.impl;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00.util.GlobalService;
import _00.util.HibernateUtils;
import community.model.Discussion;
import community.model.MemberUploadImage;
import community.model.Point;
import community.model.Reply;
import community.repository.DiscussionDao;
import register.model.MemberBean;

public class DiscussionDaoImpl implements DiscussionDao {

	SessionFactory factory = null;

	public DiscussionDaoImpl() {
		this.factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Integer saveDiscussion(Discussion discussion) {
		Integer id = null;
		Session session = factory.getCurrentSession();

		id = (Integer) session.save(discussion);

		return id;
	}

	@Override
	public Discussion queryDiscussion(Integer id) {
		Discussion discussion = null;
		Session session = factory.getCurrentSession();

		discussion = session.get(Discussion.class, id);

		return discussion;
	}

	@Override
	public Clob getContentText(Integer id) {
		Clob clob = null;
		String hql = "SELECT content FROM Discussion WHERE id = :id";
		Session session = factory.getCurrentSession();

		try {
			clob = (Clob) session.createQuery(hql).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			clob = null;
		}

		return clob;
	}

	@Override
	public boolean deleteDiscussion(Discussion discussion) {
		boolean b = false;
		Session session = factory.getCurrentSession();

		try {
			session.delete(discussion);
			b = true;
		} catch (Exception e) {
			b = false;
		}

		return b;
	}

	@Override
	public Discussion preModifyDiscussion(MemberBean mb, Integer id) {
		Discussion d = null;
		String hql = "FROM Discussion WHERE author = :mb AND id = :id";
		Session session = factory.getCurrentSession();

		try {
			d = (Discussion) session.createQuery(hql).setParameter("mb", mb).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			d = null;
		}

		return d;
	}

	@Override
	public Integer saveReply(Reply reply) {
		Integer id = null;
		Session session = factory.getCurrentSession();

		id = (Integer) session.save(reply);

		return id;
	}

	@Override
	public Integer deleteReply(Discussion d) {
		Integer i = null;
		String hql = "DELETE FROM Reply WHERE discussion = :discussion";
		Session session = factory.getCurrentSession();

		try {

			i = session.createQuery(hql).setParameter("discussion", d).executeUpdate();

		} catch (Exception e) {
			i = null;
		}

		return i;
	}

	@Override
	public List<Reply> getReplyList(Discussion discussion) {
		List<Reply> list = null;
		String hql = "FROM Reply WHERE discussion = :discussion";
		Session session = factory.getCurrentSession();

		list = session.createQuery(hql).setParameter("discussion", discussion).getResultList();

		return list;
	}

	@Override
	public Clob getReplyContentText(Integer id) {
		Clob clob = null;
		String hql = "SELECT content FROM Reply WHERE id = :id";
		Session session = factory.getCurrentSession();

		try {
			clob = (Clob) session.createQuery(hql).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			clob = null;
		}

		return clob;
	}

	@Override
	public MemberBean queryMember(Integer id) {
		MemberBean mb = null;
		Session session = factory.getCurrentSession();

		mb = session.get(MemberBean.class, id);

		return mb;
	}

	@Override
	public boolean updateDiscussion(Discussion discussion) {
		boolean b = false;
		Session session = factory.getCurrentSession();

		try {
			session.update(discussion);
			b = true;
		} catch (Exception e) {
			b = false;
		}

		return b;
	}

	@Override
	public Integer saveMemberUploadImage(MemberUploadImage mui) {
		Integer id = null;
		Session session = factory.getCurrentSession();

		id = (Integer) session.save(mui);

		return id;
	}

	@Override
	public MemberUploadImage queryMemberUploadImage(Integer id) {
		MemberUploadImage mui = null;
		Session session = factory.getCurrentSession();

		mui = session.get(MemberUploadImage.class, id);

		return mui;
	}

	@Override
	public List<Discussion> getDiscussionList(String sort, Integer page) {
		List<Discussion> list = new ArrayList<Discussion>();
		Session session = factory.getCurrentSession();

		int recordsPerPage = GlobalService.RECORDS_PER_PAGE;
		int startRecordNo = (page - 1) * recordsPerPage;

//		String column = "id";
//		String type = "DESC";
//		String hql = "FROM Discussion ORDER BY :column " + type;
//		if (sort.equalsIgnoreCase("new")) {
//			column = "id";
//			type = "DESC";
//		} else if (sort.equalsIgnoreCase("old")) {
//			column = "id";
//			type = "ASC";
//		}
//		try {
//			list = session.createQuery(hql)
//					.setParameter("column", column)
//					.setFirstResult(startRecordNo)
//					.setMaxResults(recordsPerPage)
//					.getResultList();
//		} catch (Exception e) {
//			list = null;
//		}

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Discussion> cq = cb.createQuery(Discussion.class);
		Root<Discussion> root = cq.from(Discussion.class);

		if (sort.equalsIgnoreCase("new")) {
			cq.orderBy(cb.desc(root.get("id")));
		} else if (sort.equalsIgnoreCase("old")) {
			cq.orderBy(cb.asc(root.get("id")));
		}

		try {
			list = session.createQuery(cq).setFirstResult(startRecordNo).setMaxResults(recordsPerPage).getResultList();
		} catch (Exception e) {
			list = null;
		}

		return list;
	}

	@Override
	public Long getDiscussionCount() {
		Long count = null;
		String hql = "SELECT count(*) FROM Discussion";
		Session session = factory.getCurrentSession();

		try {
			count = (Long) session.createQuery(hql).getSingleResult();
		} catch (Exception e) {
			count = null;
		}

		return count;
	}

	@Override
	public Integer savePoint(Point point) {
		Integer id = null;
		Session session = factory.getCurrentSession();

		id = (Integer) session.save(point);

		return id;
	}

	@Override
	public Long getTotalPoint(Integer discussionId, Integer replyId) {
		Long total = null;
		
		if(replyId == null) {
			total = getTotalPoint(discussionId);
			return total;
		}
		
		String hql = "SELECT sum(pointVal) FROM Point WHERE discussion_id = :discussionId AND reply_id = :replyId";
		Session session = factory.getCurrentSession();

		try {
			total = (Long) session.createQuery(hql).setParameter("discussionId", discussionId)
					.setParameter("replyId", replyId).getSingleResult();
		} catch (Exception e) {
			total = null;
		}

		return total;
	}

	@Override
	public Long getTotalPoint(Integer discussionId) {
		Long total = null;
		String hql = "SELECT sum(pointVal) FROM Point WHERE discussion_id = :discussionId AND reply_id IS NULL";
		Session session = factory.getCurrentSession();

		try {
			total = (Long) session.createQuery(hql).setParameter("discussionId", discussionId).getSingleResult();
		} catch (Exception e) {
			total = null;
		}

		return total;
	}

	@Override
	public Point queryPointRecord(MemberBean member, Integer discussionId, Integer replyId) {
		Point p = null;

		if (replyId == null) {
			p = queryPointRecord(member, discussionId);
			return p;
		}

		String hql = "FROM Point WHERE member_seqNo = :memberPkey AND discussion_id = :discussionId AND reply_id = :replyId";
		Session session = factory.getCurrentSession();

		try {
			p = (Point) session.createQuery(hql).setParameter("memberPkey", member.getPkey())
					.setParameter("discussionId", discussionId).setParameter("replyId", replyId).getSingleResult();
		} catch (Exception e) {
			p = null;
		}

		return p;
	}

	@Override
	public Point queryPointRecord(MemberBean member, Integer discussionId) {
		Point p = null;
		String hql = "FROM Point WHERE member_seqNo = :memberPkey AND discussion_id = :discussionId AND reply_id IS NULL";
		Session session = factory.getCurrentSession();
		try {
			p = (Point) session.createQuery(hql).setParameter("memberPkey", member)
					.setParameter("discussionId", discussionId).getSingleResult();
		} catch (Exception e) {
			p = null;
		}

		return p;
	}

	@Override
	public Boolean deletePoint(Point point) {
		Boolean b = false;
		Session session = factory.getCurrentSession();

		try {
			session.delete(point);
			b = true;
		} catch (Exception e) {
			b = false;
		}

		return b;
	}

	@Override
	public Integer deleteDiscussionPoint(Discussion discussion) {
		Integer i = null;
		String hql = "DELETE FROM Point WHERE discussion = :discussion";
		Session session = factory.getCurrentSession();

		try {

			i = session.createQuery(hql).setParameter("discussion", discussion).executeUpdate();

		} catch (Exception e) {
			i = null;
		}

		return i;
	}

}
