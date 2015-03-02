package com.goopai.app.bean;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.goopai.app.uitil.StringUtils;

import android.util.Xml;

public class News {

	public final static String NODE_ID = "id";
	public final static String NODE_TITLE = "title";
	public final static String NODE_URL = "url";
	public final static String NODE_BODY = "body";
	public final static String NODE_AUTHORID = "authorid";
	public final static String NODE_AUTHOR = "author";
	public final static String NODE_PUBDATE = "pubDate";
	public final static String NODE_COMMENTCOUNT = "commentCount";
	public final static String NODE_FAVORITE = "favorite";
	public final static String NODE_START = "news";

	public final static String NODE_SOFTWARELINK = "softwarelink";
	public final static String NODE_SOFTWARENAME = "softwarename";

	public final static String NODE_NEWSTYPE = "newstype";
	public final static String NODE_TYPE = "type";
	public final static String NODE_ATTACHMENT = "attachment";
	public final static String NODE_AUTHORUID2 = "authoruid2";

	public final static int NEWSTYPE_NEWS = 0x00;// 0 新闻
	public final static int NEWSTYPE_SOFTWARE = 0x01;// 1 软件
	public final static int NEWSTYPE_POST = 0x02;// 2 帖子
	public final static int NEWSTYPE_BLOG = 0x03;// 3 博客

	private String title;
	private String url;
	private String body;
	private String author;
	private String pubDate;
	private String softwareLink;
	private String softwareName;
	private int authorId;
	private int recommentCount;
	private int favorite;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getSoftwareLink() {
		return softwareLink;
	}

	public void setSoftwareLink(String softwareLink) {
		this.softwareLink = softwareLink;
	}

	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getRecommentCount() {
		return recommentCount;
	}

	public void setRecommentCount(int recommentCount) {
		this.recommentCount = recommentCount;
	}

	public int getFavorite() {
		return favorite;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}

	public static News parse(InputStream stream) throws IOException {
		News news = null;
		XmlPullParser xmlParser = Xml.newPullParser();
		try {
			xmlParser.setInput(stream, "UTF-8");
			int eventType = xmlParser.getEventType();
			while (eventType != xmlParser.END_DOCUMENT) {
				String tag = xmlParser.getName();
				switch (eventType) {
				case XmlPullParser.START_TAG:
					if (tag.equalsIgnoreCase(NODE_START)) {
						news = new News();
					} else if (news != null) {
						if (tag.equalsIgnoreCase(NODE_ID)) {

						} else if (tag.equalsIgnoreCase(NODE_TITLE)) {
							news.setTitle(xmlParser.nextText());
						} else if (tag.equalsIgnoreCase(NODE_URL)) {
							news.setUrl(xmlParser.nextText());
						} else if (tag.equalsIgnoreCase(NODE_BODY)) {
							news.setBody(xmlParser.nextText());
						} else if (tag.equalsIgnoreCase(NODE_AUTHOR)) {
							news.setAuthor(xmlParser.nextText());
						} else if (tag.equalsIgnoreCase(NODE_AUTHORID)) {
							news.setAuthorId(StringUtils.toInt(xmlParser.nextText(), 0));
						} else if (tag.equalsIgnoreCase(NODE_COMMENTCOUNT)) {
							news.setRecommentCount(StringUtils.toInt(xmlParser.nextText(), 0));
						} else if (tag.equalsIgnoreCase(NODE_PUBDATE)) {
							news.setPubDate(xmlParser.nextText());
						} else if (tag.equalsIgnoreCase(NODE_SOFTWARELINK)) {
							news.setSoftwareLink(xmlParser.nextText());
						} else if (tag.equalsIgnoreCase(NODE_SOFTWARENAME)) {
							news.setSoftwareName(xmlParser.nextText());
						} else if (tag.equalsIgnoreCase(NODE_FAVORITE)) {
							news.setFavorite(StringUtils.toInt(xmlParser.nextText(), 0));
						}
					}
					break;
				case XmlPullParser.END_TAG:
					break;
				}
				eventType = xmlParser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return news;
	}
}
