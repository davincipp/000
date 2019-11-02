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
import community.model.Discussion;
import community.model.MemberUploadImage;
import community.model.Point;
import community.model.Reply;
import community.repository.DiscussionDao;
import community.repository.impl.DiscussionDaoImpl;
import community.service.DiscussionService;
import register.model.MemberBean;

public class DiscussionServiceImpl implements DiscussionService {

	DiscussionDao dao;
	SessionFactory factory;

	public DiscussionServiceImpl() {
		this.dao = new DiscussionDaoImpl();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Integer saveDiscussion(Discussion discussion) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		Integer id;
		try {
			tx = session.beginTransaction();

			id = dao.saveDiscussion(discussion);

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
	public Discussion queryDiscussion(Integer id) {
		Discussion d = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			d = dao.queryDiscussion(id);

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
	public boolean deleteDiscussion(MemberBean mb, Integer id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		boolean b = false;
		try {
			tx = session.beginTransaction();

			Discussion d = dao.preModifyDiscussion(mb, id);
			if (d != null) {
				dao.deleteDiscussionPoint(d);
				dao.deleteReply(d);
				b = dao.deleteDiscussion(d);
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
	public Discussion preUpdateDiscussion(MemberBean mb, Integer id) {
		Discussion d = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			d = dao.preModifyDiscussion(mb, id);

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
	public Integer saveReply(Reply reply) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		Integer id;
		try {
			tx = session.beginTransaction();

			id = dao.saveReply(reply);

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
	public List<Reply> getReplyList(Discussion discussion) {
		List<Reply> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			list = dao.getReplyList(discussion);

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
	public List<Discussion> getDiscussionList(String sort, Integer page) {
		List<Discussion> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			list = dao.getDiscussionList(sort, page);

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

			count = dao.getDiscussionCount();
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
	public boolean updateDiscussion(MemberBean mb, Integer intDid, String title, Clob content, Blob coverImage,
			String coverImageName, Timestamp deadline) {
		boolean b = false;
		Discussion d = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			d = dao.queryDiscussion(intDid);
			if (mb.getPkey() == d.getAuthor().getPkey()) {
				d.setTitle(title);
				d.setContent(content);
				
				if (coverImageName != null && coverImageName.trim().length() > 0) {
					d.setCoverImage(coverImage);
					d.setCoverImageName(coverImageName);
				}
				d.setDeadline(deadline);

				b = dao.updateDiscussion(d);
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
	public Integer savePoint(Point point) {
		Integer pointType = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Integer id = dao.savePoint(point);
			if (id != null)
				pointType = point.getPointVal();

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return pointType;
	}

	@Override
	public Integer getPointType(MemberBean member, Integer discussionId, Integer replyId) {
		Integer pointType = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Point point = dao.queryPointRecord(member, discussionId, replyId);
			pointType = point.getPointVal();

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return pointType;
	}

	@Override
	public Boolean preModifyPointRecord(MemberBean member, Integer discussionId, Integer replyId) {
		Boolean deleteRecord = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Point p = dao.queryPointRecord(member, discussionId, replyId);

			if (p != null) {
				deleteRecord = dao.deletePoint(p);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return deleteRecord;
	}

	@Override
	public Long getTotalPoint(Integer discussionId, Integer replyId) {

		Long total = 0L;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			total = dao.getTotalPoint(discussionId, replyId);

			if (total == null)
				total = 0L;

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return total;
	}

}
