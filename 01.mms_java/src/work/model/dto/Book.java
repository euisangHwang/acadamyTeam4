package work.model.dto;

/**
 * <pre> 
 * 회원 도메인 클래스 모델링
 * 
 * ## 자바 적용 기술
 * -- 클래스 선언, 멤버변수, 메서드, 생성자
 * -- 데이터타입  : 기본형, 객체형
 * -- Encapsulation : 은닉성(데이터, 알고리즘  information hiding) - private
 * -- 접근권한 (Access modifier) : public, protected, default(friendly), private
 * -- DAO =>  직렬화 객체
 * 
 * ## 회원 property(멤버변수)
 * 1.아이디 	문자열 : memberId
 * 2.비밀번호	문자열 : memberPw
 * 3.이름		문자열 : memberName
 * 4.연락처	문자열 (기본형식 : 010-1234-1234) : mobile
 * 5.이메일	문자열 : email
 * 6.가입일	문자열 (기본형식 : 년도4자리, 월2자리, 일2자리) : entryDate
 * 7.등급		문자열	(회원종류 : 일반(G), 우수(S), 관리자(M)) : grade
 * 8.마일리지	숫자	: 일반회원 : mileage
 * 9.담당자	문자열 : 우수회원 : manager
 * </pre>
 * 
 * @author 황의성
 *@version ver 1.0
 *@since jdk1.8
 */

/*실습 1 : Member 클래스 작성
 * -- encapsulatiuon
 * -- 직렬화객체
 * -- 생성자 중복정의
 * -- => 기본생성자
 * 	  => 전체데이터 초기화생성자
 *    => 필수데이터 초기생성자 ()*/

public class Book {

	private int bookNum;
	private String bookName;
	private String authorName;
	private int publisherNum;
	private int bookPrice;
	private int stock;
	private String publishDate;
	private String category;
	
	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Book () {
		
	}
	
	public Book (int bookNum, String bookName, String authorName, int publisherNum, int bookPrice,
			int stock, String publishDate, String category) {
		
		this.bookNum = bookNum;
		this.bookName = bookName;
		this.authorName = authorName;
		this.publisherNum = publisherNum;
		this.bookPrice = bookPrice;
		this.stock = stock;
		this.publishDate = publishDate;
		this.category =category;
	}

	/**
	 * @return the bookNum
	 */
	public int getBookNum() {
		return bookNum;
	}

	/**
	 * @param bookNum the bookNum to set
	 */
	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * @param bookName the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * @return the publisherNum
	 */
	public int getPublisherNum() {
		return publisherNum;
	}

	/**
	 * @param publisherNum the publisherNum to set
	 */
	public void setPublisherNum(int publisherNum) {
		this.publisherNum = publisherNum;
	}

	/**
	 * @return the bookPrice
	 */
	public int getBookPrice() {
		return bookPrice;
	}

	/**
	 * @param bookPrice the bookPrice to set
	 */
	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return the publishDate
	 */
	public String getPublishDate() {
		return publishDate;
	}

	/**
	 * @param publishDate the publishDate to set
	 */
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [bookNum=");
		builder.append(bookNum);
		builder.append(", bookName=");
		builder.append(bookName);
		builder.append(", authorName=");
		builder.append(authorName);
		builder.append(", publisherNum=");
		builder.append(publisherNum);
		builder.append(", bookPrice=");
		builder.append(bookPrice);
		builder.append(", stock=");
		builder.append(stock);
		builder.append(", publishDate=");
		builder.append(publishDate);
		builder.append(", category=");
		builder.append(category);
		builder.append("]");
		return builder.toString();
	}
	


}
