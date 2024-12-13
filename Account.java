import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SbiBankSystem")
public class Account {
	@Id
	@Column(name="Accno",length = 10)
	private long accNO;
	@Column(name="name",length = 20)
	private String name;
	@Column(name="mobNo",length = 10)
	private long mobNO;
	@Column(name="AdharNo",length = 12)
	private long adharNO;
	@Column(name="gender",length = 10)
	private String gender;
	@Column(name="age",length = 3)
	private int age;
	@Column(name="balance",length = 10)
	private double balance;
	@Column(name="password",length = 6)
	 private String password;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Account(long accNO, String name, long mobNO, long adharNO, String gender, int age) {
		super();
		this.accNO = accNO;
		this.name = name;
		this.mobNO = mobNO;
		this.adharNO = adharNO;
		this.gender = gender;
		this.age = age;
	}
	
	public long getAccNO() {
		return accNO;
	}
	public void setAccNO(long accNO) {
		this.accNO = accNO;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobNO() {
		return mobNO;
	}
	public void setMobNO(long mobNO) {
		this.mobNO = mobNO;
	}
	public long getAdharNO() {
		return adharNO;
	}
	public void setAdharNO(long adharNO) {
		this.adharNO = adharNO;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Account [accNO=" + accNO + ", name=" + name + ", mobNO=" + mobNO + ", adharNO=" + adharNO + ", gender="
				+ gender + ", age=" + age + ", balance=" + balance + ", password=" + password + "]";
	}

	
	
	
}
