package community.service.impl;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00.util.GlobalService;
import _00.util.HibernateUtils;
import community.model.Activity;
import community.model.ActivityReply;
import community.model.MemberUploadImage;
import community.model.Reply;
import community.repository.ActivityDao;
import community.repository.impl.ActivityDaoImpl;
import community.service.ActivityService;
import register.model.MemberBean;

public class ActivityServiceImpl implements ActivityService {

	ActivityDao dao;
	SessionFactory factory;

	public ActivityServiceImpl() {
		this.dao = new ActivityDaoImpl();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Integer saveActivity(Activity activity) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		Integer id;
		try {
			tx = session.beginTransaction();

			id = dao.saveActivity(activity);

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return id;
	}

	@Override
	public Activity queryActivity(Integer id) {
		Activity d = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			d = dao.queryActivity(id);

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return d;
	}

	@Override
	public Clob getContentText(Integer id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		Clob clob;
		try {
			tx = session.beginTransaction();

			clob = dao.getContentText(id);

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return clob;
	}

	@Override
	public boolean deleteActivity(MemberBean mb, Integer id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		boolean b = false;
		try {
			tx = session.beginTransaction();

			Activity d = dao.preModifyActivity(mb, id);
			if (d != null) {
//				dao.deleteActivityPoint(d);
				dao.deleteReply(d);
				b = dao.deleteActivity(d);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return b;
	}

	@Override
	public Activity preUpdateActivity(MemberBean mb, Integer id) {
		Activity d = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			d = dao.preModifyActivity(mb, id);

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return d;
	}

	@Override
	public Integer saveReply(ActivityReply reply) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		Integer id;
		try {
			tx = session.beginTransaction();

			id = dao.saveActivityReply(reply);

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return id;
	}

	@Override
	public List<Reply> getReplyList(Activity activity) {
		List<Reply> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			list = dao.getReplyList(activity);

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public Clob getReplyContentText(Integer id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		Clob clob;
		try {
			tx = session.beginTransaction();

			clob = dao.getReplyContentText(id);

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return clob;
	}

	@Override
	public MemberBean queryMember(Integer id) {
		MemberBean mb = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			mb = dao.queryMember(id);

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return mb;
	}

	@Override
	public Integer saveMemberUploadImage(MemberUploadImage mui) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		Integer id;
		try {
			tx = session.beginTransaction();

			id = dao.saveMemberUploadImage(mui);

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return id;
	}

	@Override
	public MemberUploadImage queryMemberUploadImage(Integer id) {
		MemberUploadImage mui = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			mui = dao.queryMemberUploadImage(id);

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return mui;
	}

	@Override
	public List<Activity> getActivityList(String sort, Integer page) {
		List<Activity> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			list = dao.getActivityList(sort, page);

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public Integer getTotalPages() {
		Integer totalPages = null;
		Long count = null;
		Integer recordsPerPage = GlobalService.RECORDS_PER_PAGE;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			count = dao.getActivityCount();
			totalPages = (int) (Math.ceil(count / (double) recordsPerPage));

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return totalPages;

	}

	@Override
	public boolean updateActivity(MemberBean mb, Integer intDid, String title, Clob content, Blob coverImage,
			String coverImageName, Timestamp deadline, Integer intPrice, String location) {
		boolean b = false;
		Activity d = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			d = dao.queryActivity(intDid);
			if (mb.getPkey() == d.getAuthor().getPkey()) {
				d.setTitle(title);
				d.setContent(content);
				
				if (coverImageName != null && coverImageName.trim().length() > 0) {
					d.setCoverImage(coverImage);
					d.setCoverImageName(coverImageName);
				}
				d.setDeadline(deadline);

				b = dao.updateActivity(d);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return b;
	}

//	@Override
//	public Integer savePoint(Point point) {
//		Integer pointType = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//
//			Integer id = dao.savePoint(point);
//			if (id != null)
//				pointType = point.getPointVal();
//
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null)
//				tx.rollback();
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//		return pointType;
//	}

//	@Override
//	public Integer getPointType(MemberBean member, Integer activityId, Integer replyId) {
//		Integer pointType = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//
//			Point point = dao.queryPointRecord(member, activityId, replyId);
//			pointType = point.getPointVal();
//
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null)
//				tx.rollback();
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//		return pointType;
//	}

//	@Override
//	public Boolean preModifyPointRecord(MemberBean member, Integer activityId, Integer replyId) {
//		Boolean deleteRecord = false;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//
//			Point p = dao.queryPointRecord(member, activityId, replyId);
//
//			if (p != null) {
//				deleteRecord = dao.deletePoint(p);
//			}
//
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null)
//				tx.rollback();
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//		return deleteRecord;
//	}

//	@Override
//	public Long getTotalPoint(Integer activityId, Integer replyId) {
//
//		Long total = 0L;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//
//			total = dao.getTotalPoint(activityId, replyId);
//
//			if (total == null)
//				total = 0L;
//
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null)
//				tx.rollback();
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//		return total;
//	}

}
