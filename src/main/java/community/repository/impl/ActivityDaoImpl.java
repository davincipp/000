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
import community.model.Activity;
import community.model.ActivityReply;
import community.model.MemberUploadImage;
import community.model.Point;
import community.model.Reply;
import community.repository.ActivityDao;
import register.model.MemberBean;

public class ActivityDaoImpl implements ActivityDao {
	SessionFactory factory = null;

	public ActivityDaoImpl() {
		this.factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Integer saveActivity(Activity activity) {
		Integer id = null;
		Session session = factory.getCurrentSession();

		id = (Integer) session.save(activity);

		return id;
	}

	@Override
	public Activity queryActivity(Integer id) {
		Activity activity = null;
		Session session = factory.getCurrentSession();

		activity = session.get(Activity.class, id);

		return activity;
	}

	@Override
	public Clob getContentText(Integer id) {
		Clob clob = null;
		String hql = "SELECT content FROM Activity WHERE id = :id";
		Session session = factory.getCurrentSession();

		try {
			clob = (Clob) session.createQuery(hql).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			clob = null;
		}

		return clob;
	}

	@Override
	public boolean deleteActivity(Activity activity) {
		boolean b = false;
		Session session = factory.getCurrentSession();

		try {
			session.delete(activity);
			b = true;
		} catch (Exception e) {
			b = false;
		}

		return b;
	}

	@Override
	public Activity preModifyActivity(MemberBean mb, Integer id) {
		Activity d = null;
		String hql = "FROM Activity WHERE author = :mb AND id = :id";
		Session session = factory.getCurrentSession();

		try {
			d = (Activity) session.createQuery(hql).setParameter("mb", mb).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			d = null;
		}

		return d;
	}

	@Override
	public Integer saveActivityReply(ActivityReply reply) {
		Integer id = null;
		Session session = factory.getCurrentSession();

		id = (Integer) session.save(reply);

		return id;
	}

	@Override
	public Integer deleteReply(Activity d) {
		Integer i = null;
		String hql = "DELETE FROM Reply WHERE activity = :activity";
		Session session = factory.getCurrentSession();

		try {

			i = session.createQuery(hql).setParameter("activity", d).executeUpdate();

		} catch (Exception e) {
			i = null;
		}

		return i;
	}

	@Override
	public List<Reply> getReplyList(Activity activity) {
		List<Reply> list = null;
		String hql = "FROM ActivityReply WHERE activity = :activity";
		Session session = factory.getCurrentSession();

		list = session.createQuery(hql).setParameter("activity", activity).getResultList();

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
	public boolean updateActivity(Activity activity) {
		boolean b = false;
		Session session = factory.getCurrentSession();

		try {
			session.update(activity);
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
	public List<Activity> getActivityList(String sort, Integer page) {
		List<Activity> list = new ArrayList<Activity>();
		Session session = factory.getCurrentSession();

		int recordsPerPage = GlobalService.RECORDS_PER_PAGE;
		int startRecordNo = (page - 1) * recordsPerPage;

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Activity> cq = cb.createQuery(Activity.class);
		Root<Activity> root = cq.from(Activity.class);

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
	public Long getActivityCount() {
		Long count = null;
		String hql = "SELECT count(*) FROM Activity";
		Session session = factory.getCurrentSession();

		try {
			count = (Long) session.createQuery(hql).getSingleResult();
		} catch (Exception e) {
			count = null;
		}

		return count;
	}

//	@Override
//	public Integer savePoint(Point point) {
//		Integer id = null;
//		Session session = factory.getCurrentSession();
//
//		id = (Integer) session.save(point);
//
//		return id;
//	}

//	@Override
//	public Long getTotalPoint(Integer activityId, Integer replyId) {
//		Long total = null;
//		
//		if(replyId == null) {
//			total = getTotalPoint(activityId);
//			return total;
//		}
//		
//		String hql = "SELECT sum(pointVal) FROM Point WHERE activity_id = :activityId AND reply_id = :replyId";
//		Session session = factory.getCurrentSession();
//
//		try {
//			total = (Long) session.createQuery(hql).setParameter("activityId", activityId)
//					.setParameter("replyId", replyId).getSingleResult();
//		} catch (Exception e) {
//			total = null;
//		}
//
//		return total;
//	}

//	@Override
//	public Long getTotalPoint(Integer activityId) {
//		Long total = null;
//		String hql = "SELECT sum(pointVal) FROM Point WHERE activity_id = :activityId AND reply_id IS NULL";
//		Session session = factory.getCurrentSession();
//
//		try {
//			total = (Long) session.createQuery(hql).setParameter("activityId", activityId).getSingleResult();
//		} catch (Exception e) {
//			total = null;
//		}
//
//		return total;
//	}

//	@Override
//	public Point queryPointRecord(MemberBean member, Integer activityId, Integer replyId) {
//		Point p = null;
//
//		if (replyId == null) {
//			p = queryPointRecord(member, activityId);
//			return p;
//		}
//
//		String hql = "FROM Point WHERE member_seqNo = :memberPkey AND activity_id = :activityId AND reply_id = :replyId";
//		Session session = factory.getCurrentSession();
//
//		try {
//			p = (Point) session.createQuery(hql).setParameter("memberPkey", member.getPkey())
//					.setParameter("activityId", activityId).setParameter("replyId", replyId).getSingleResult();
//		} catch (Exception e) {
//			p = null;
//		}
//
//		return p;
//	}

//	@Override
//	public Point queryPointRecord(MemberBean member, Integer activityId) {
//		Point p = null;
//		String hql = "FROM Point WHERE member_seqNo = :memberPkey AND activity_id = :activityId AND reply_id IS NULL";
//		Session session = factory.getCurrentSession();
//		try {
//			p = (Point) session.createQuery(hql).setParameter("memberPkey", member)
//					.setParameter("activityId", activityId).getSingleResult();
//		} catch (Exception e) {
//			p = null;
//		}
//
//		return p;
//	}

//	@Override
//	public Boolean deletePoint(Point point) {
//		Boolean b = false;
//		Session session = factory.getCurrentSession();
//
//		try {
//			session.delete(point);
//			b = true;
//		} catch (Exception e) {
//			b = false;
//		}
//
//		return b;
//	}

//	@Override
//	public Integer deleteActivityPoint(Activity activity) {
//		Integer i = null;
//		String hql = "DELETE FROM Point WHERE activity = :activity";
//		Session session = factory.getCurrentSession();
//
//		try {
//
//			i = session.createQuery(hql).setParameter("activity", activity).executeUpdate();
//
//		} catch (Exception e) {
//			i = null;
//		}
//
//		return i;
//	}


}
