package com.pusilkom.base.model;

import java.io.Serializable;

public class TipeKerjasama implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tipe_kerjasama.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tipe_kerjasama.nama
     *
     * @mbg.generated
     */
    private String nama;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tipe_kerjasama
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tipe_kerjasama.id
     *
     * @return the value of tipe_kerjasama.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tipe_kerjasama.id
     *
     * @param id the value for tipe_kerjasama.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tipe_kerjasama.nama
     *
     * @return the value of tipe_kerjasama.nama
     *
     * @mbg.generated
     */
    public String getNama() {
        return nama;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tipe_kerjasama.nama
     *
     * @param nama the value for tipe_kerjasama.nama
     *
     * @mbg.generated
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tipe_kerjasama
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
        TipeKerjasama other = (TipeKerjasama) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNama() == null ? other.getNama() == null : this.getNama().equals(other.getNama()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tipe_kerjasama
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNama() == null) ? 0 : getNama().hashCode());
        return result;
    }
}