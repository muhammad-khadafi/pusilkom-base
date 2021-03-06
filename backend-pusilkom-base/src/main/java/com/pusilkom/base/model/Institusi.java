package com.pusilkom.base.model;

import java.io.Serializable;

public class Institusi implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institusi.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institusi.id_parent_institusi
     *
     * @mbg.generated
     */
    private Integer idParentInstitusi;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institusi.id_negara
     *
     * @mbg.generated
     */
    private Integer idNegara;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institusi.id_tipe_institusi
     *
     * @mbg.generated
     */
    private Integer idTipeInstitusi;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institusi.kode_sso
     *
     * @mbg.generated
     */
    private String kodeSso;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institusi.nama
     *
     * @mbg.generated
     */
    private String nama;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institusi.alamat
     *
     * @mbg.generated
     */
    private String alamat;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institusi.is_unhan
     *
     * @mbg.generated
     */
    private Boolean isUnhan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table institusi
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column institusi.id
     *
     * @return the value of institusi.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column institusi.id
     *
     * @param id the value for institusi.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column institusi.id_parent_institusi
     *
     * @return the value of institusi.id_parent_institusi
     *
     * @mbg.generated
     */
    public Integer getIdParentInstitusi() {
        return idParentInstitusi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column institusi.id_parent_institusi
     *
     * @param idParentInstitusi the value for institusi.id_parent_institusi
     *
     * @mbg.generated
     */
    public void setIdParentInstitusi(Integer idParentInstitusi) {
        this.idParentInstitusi = idParentInstitusi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column institusi.id_negara
     *
     * @return the value of institusi.id_negara
     *
     * @mbg.generated
     */
    public Integer getIdNegara() {
        return idNegara;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column institusi.id_negara
     *
     * @param idNegara the value for institusi.id_negara
     *
     * @mbg.generated
     */
    public void setIdNegara(Integer idNegara) {
        this.idNegara = idNegara;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column institusi.id_tipe_institusi
     *
     * @return the value of institusi.id_tipe_institusi
     *
     * @mbg.generated
     */
    public Integer getIdTipeInstitusi() {
        return idTipeInstitusi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column institusi.id_tipe_institusi
     *
     * @param idTipeInstitusi the value for institusi.id_tipe_institusi
     *
     * @mbg.generated
     */
    public void setIdTipeInstitusi(Integer idTipeInstitusi) {
        this.idTipeInstitusi = idTipeInstitusi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column institusi.kode_sso
     *
     * @return the value of institusi.kode_sso
     *
     * @mbg.generated
     */
    public String getKodeSso() {
        return kodeSso;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column institusi.kode_sso
     *
     * @param kodeSso the value for institusi.kode_sso
     *
     * @mbg.generated
     */
    public void setKodeSso(String kodeSso) {
        this.kodeSso = kodeSso;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column institusi.nama
     *
     * @return the value of institusi.nama
     *
     * @mbg.generated
     */
    public String getNama() {
        return nama;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column institusi.nama
     *
     * @param nama the value for institusi.nama
     *
     * @mbg.generated
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column institusi.alamat
     *
     * @return the value of institusi.alamat
     *
     * @mbg.generated
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column institusi.alamat
     *
     * @param alamat the value for institusi.alamat
     *
     * @mbg.generated
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column institusi.is_unhan
     *
     * @return the value of institusi.is_unhan
     *
     * @mbg.generated
     */
    public Boolean getIsUnhan() {
        return isUnhan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column institusi.is_unhan
     *
     * @param isUnhan the value for institusi.is_unhan
     *
     * @mbg.generated
     */
    public void setIsUnhan(Boolean isUnhan) {
        this.isUnhan = isUnhan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table institusi
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Institusi other = (Institusi) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIdParentInstitusi() == null ? other.getIdParentInstitusi() == null : this.getIdParentInstitusi().equals(other.getIdParentInstitusi()))
            && (this.getIdNegara() == null ? other.getIdNegara() == null : this.getIdNegara().equals(other.getIdNegara()))
            && (this.getIdTipeInstitusi() == null ? other.getIdTipeInstitusi() == null : this.getIdTipeInstitusi().equals(other.getIdTipeInstitusi()))
            && (this.getKodeSso() == null ? other.getKodeSso() == null : this.getKodeSso().equals(other.getKodeSso()))
            && (this.getNama() == null ? other.getNama() == null : this.getNama().equals(other.getNama()))
            && (this.getAlamat() == null ? other.getAlamat() == null : this.getAlamat().equals(other.getAlamat()))
            && (this.getIsUnhan() == null ? other.getIsUnhan() == null : this.getIsUnhan().equals(other.getIsUnhan()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table institusi
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIdParentInstitusi() == null) ? 0 : getIdParentInstitusi().hashCode());
        result = prime * result + ((getIdNegara() == null) ? 0 : getIdNegara().hashCode());
        result = prime * result + ((getIdTipeInstitusi() == null) ? 0 : getIdTipeInstitusi().hashCode());
        result = prime * result + ((getKodeSso() == null) ? 0 : getKodeSso().hashCode());
        result = prime * result + ((getNama() == null) ? 0 : getNama().hashCode());
        result = prime * result + ((getAlamat() == null) ? 0 : getAlamat().hashCode());
        result = prime * result + ((getIsUnhan() == null) ? 0 : getIsUnhan().hashCode());
        return result;
    }
}