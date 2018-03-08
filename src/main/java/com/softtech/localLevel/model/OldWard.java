package com.softtech.localLevel.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class OldWard implements Serializable {

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Long id;
		private Long oldWard;
		
		@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.MERGE})
		@JsonBackReference
		@JoinColumn(name="newWard_id")
		private NewWard newWard;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getOldWard() {
			return oldWard;
		}

		public void setOldWard(Long oldWard) {
			this.oldWard = oldWard;
		}

		public NewWard getNewWard() {
			return newWard;
		}

		public void setNewWard(NewWard newWard) {
			this.newWard = newWard;
		}

		public OldWard(Long id, Long oldWard, NewWard newWard) {
			super();
			this.id = id;
			this.oldWard = oldWard;
			this.newWard = newWard;
		}

		public OldWard() {
			super();
		}

		
		
}
