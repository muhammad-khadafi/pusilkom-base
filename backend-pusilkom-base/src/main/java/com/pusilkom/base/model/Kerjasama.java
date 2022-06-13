package com.pusilkom.base.model;

import java.io.Serializable;
import java.util.Date;

public class Kerjasama implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kerjasama.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kerjasama.id_tipe_kerjasama
     *
     * @mbg.generated
     */
    private Integer idTipeKerjasama;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kerjasama.id_pihak_pertama
     *
     * @mbg.generated
     */
    private Integer idPihakPertama;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kerjasama.id_pihak_kedua
     *
     * @mbg.generated
     */
    private Integer idPihakKedua;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kerjasama.judul
     *
     * @mbg.generated
     */
    private String judul;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kerjasama.deskripsi
     *
     * @mbg.generated
     */
    private String deskripsi;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kerjasama.tanggal_perjanjian
     *
     * @mbg.generated
     */
    private Date tanggalPerjanjian;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kerjasama.tanggal_mulai
     *
     * @mbg.generated
     */
    private Date tanggalMulai;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kerjasama.tanggal_selesai
     *
     * @mbg.generated
     */
    private Date tanggalSelesai;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kerjasama
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kerjasama.id
     *
     * @return the value of kerjasama.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kerjasama.id
     *
     * @param id the value for kerjasama.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kerjasama.id_tipe_kerjasama
     *
     * @return the value of kerjasama.id_tipe_kerjasama
     *
     * @mbg.generated
     */
    public Integer getIdTipeKerjasama() {
        return idTipeKerjasama;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kerjasama.id_tipe_kerjasama
     *
     * @param idTipeKerjasama the value for kerjasama.id_tipe_kerjasama
     *
     * @mbg.generated
     */
    public void setIdTipeKerjasama(Integer idTipeKerjasama) {
        this.idTipeKerjasama = idTipeKerjasama;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kerjasama.id_pihak_pertama
     *
     * @return the value of kerjasama.id_pihak_pertama
     *
     * @mbg.generated
     */
    public Integer getIdPihakPertama() {
        return idPihakPertama;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kerjasama.id_pihak_pertama
     *
     * @param idPihakPertama the value for kerjasama.id_pihak_pertama
     *
     * @mbg.generated
     */
    public void setIdPihakPertama(Integer idPihakPertama) {
        this.idPihakPertama = idPihakPertama;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kerjasama.id_pihak_kedua
     *
     * @return the value of kerjasama.id_pihak_kedua
     *
     * @mbg.generated
     */
    public Integer getIdPihakKedua() {
        return idPihakKedua;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kerjasama.id_pihak_kedua
     *
     * @param idPihakKedua the value for kerjasama.id_pihak_kedua
     *
     * @mbg.generated
     */
    public void setIdPihakKedua(Integer idPihakKedua) {
        this.idPihakKedua = idPihakKedua;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kerjasama.judul
     *
     * @return the value of kerjasama.judul
     *
     * @mbg.generated
     */
    public String getJudul() {
        return judul;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kerjasama.judul
     *
     * @param judul the value for kerjasama.judul
     *
     * @mbg.generated
     */
    public void setJudul(String judul) {
        this.judul = judul;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kerjasama.deskripsi
     *
     * @return the value of kerjasama.deskripsi
     *
     * @mbg.generated
     */
    public String getDeskripsi() {
        return deskripsi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kerjasama.deskripsi
     *
     * @param deskripsi the value for kerjasama.deskripsi
     *
     * @mbg.generated
     */
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kerjasama.tanggal_perjanjian
     *
     * @return the value of kerjasama.tanggal_perjanjian
     *
     * @mbg.generated
     */
    public Date getTanggalPerjanjian() {
        return tanggalPerjanjian;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kerjasama.tanggal_perjanjian
     *
     * @param tanggalPerjanjian the value for kerjasama.tanggal_perjanjian
     *
     * @mbg.generated
     */
    public void setTanggalPerjanjian(Date tanggalPerjanjian) {
        this.tanggalPerjanjian = tanggalPerjanjian;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kerjasama.tanggal_mulai
     *
     * @return the value of kerjasama.tanggal_mulai
     *
     * @mbg.generated
     */
    public Date getTanggalMulai() {
        return tanggalMulai;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kerjasama.tanggal_mulai
     *
     * @param tanggalMulai the value for kerjasama.tanggal_mulai
     *
     * @mbg.generated
     */
    public void setTanggalMulai(Date tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kerjasama.tanggal_selesai
     *
     * @return the value of kerjasama.tanggal_selesai
     *
     * @mbg.generated
     */
    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kerjasama.tanggal_selesai
     *
     * @param tanggalSelesai the value for kerjasama.tanggal_selesai
     *
     * @mbg.generated
     */
    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kerjasama
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
        Kerjasama other = (Kerjasama) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIdTipeKerjasama() == null ? other.getIdTipeKerjasama() == null : this.getIdTipeKerjasama().equals(other.getIdTipeKerjasama()))
            && (this.getIdPihakPertama() == null ? other.getIdPihakPertama() == null : this.getIdPihakPertama().equals(other.getIdPihakPertama()))
            && (this.getIdPihakKedua() == null ? other.getIdPihakKedua() == null : this.getIdPihakKedua().equals(other.getIdPihakKedua()))
            && (this.getJudul() == null ? other.getJudul() == null : this.getJudul().equals(other.getJudul()))
            && (this.getDeskripsi() == null ? other.getDeskripsi() == null : this.getDeskripsi().equals(other.getDeskripsi()))
            && (this.getTanggalPerjanjian() == null ? other.getTanggalPerjanjian() == null : this.getTanggalPerjanjian().equals(other.getTanggalPerjanjian()))
            && (this.getTanggalMulai() == null ? other.getTanggalMulai() == null : this.getTanggalMulai().equals(other.getTanggalMulai()))
            && (this.getTanggalSelesai() == null ? other.getTanggalSelesai() == null : this.getTanggalSelesai().equals(other.getTanggalSelesai()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kerjasama
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIdTipeKerjasama() == null) ? 0 : getIdTipeKerjasama().hashCode());
        result = prime * result + ((getIdPihakPertama() == null) ? 0 : getIdPihakPertama().hashCode());
        result = prime * result + ((getIdPihakKedua() == null) ? 0 : getIdPihakKedua().hashCode());
        result = prime * result + ((getJudul() == null) ? 0 : getJudul().hashCode());
        result = prime * result + ((getDeskripsi() == null) ? 0 : getDeskripsi().hashCode());
        result = prime * result + ((getTanggalPerjanjian() == null) ? 0 : getTanggalPerjanjian().hashCode());
        result = prime * result + ((getTanggalMulai() == null) ? 0 : getTanggalMulai().hashCode());
        result = prime * result + ((getTanggalSelesai() == null) ? 0 : getTanggalSelesai().hashCode());
        return result;
    }
}