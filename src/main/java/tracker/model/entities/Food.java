package tracker.model.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="food")
public class Food implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 18L;
	private Long id;
	private String nome;
	private String descrizione;
	private int calorie;
	private int carboidrati;
	private int proteine;
	private int grassi;
	
	
	private Set<Meal> pasti;
	
	
	private User user;
	

	public void setId(Long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", updatable=false, nullable=false)
	public Long getId() {
		return id;
	}
	@Column(name="NOME")
	public String getNome() {
		return nome;
	}
	
	@Column(name="DESCRIZIONE")
	public String getDescrizione() {
		return descrizione;
	}
	
	@Column(name="CALORIE")
	public int getCalorie() {
		return calorie;
	}
	
	@Column(name="CARBOIDRATI")
	public int getCarboidrati() {
		return carboidrati;
	}
	
	@Column(name="PROTEINE")
	public int getProteine() {
		return proteine;
	}
	
	@Column(name="GRASSI")
	public int getGrassi() {
		return grassi;
	}
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}
	public void setCarboidrati(int carboidrati) {
		this.carboidrati = carboidrati;
	}
	public void setProteine(int proteine) {
		this.proteine = proteine;
	}
	public void setGrassi(int grassi) {
		this.grassi = grassi;
	}
	
	@ManyToMany(mappedBy = "foods")
	public Set<Meal> getPasti() {
		return pasti;
	}

	public void setPasti(Set<Meal> pasti) {
		this.pasti = pasti;
	}
	
	@ManyToOne
	@JoinColumn(name="USER")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
