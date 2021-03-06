package com.pusilkom.base.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KerjasamaExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kerjasama
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kerjasama
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kerjasama
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kerjasama
     *
     * @mbg.generated
     */
    public KerjasamaExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kerjasama
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kerjasama
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kerjasama
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kerjasama
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kerjasama
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kerjasama
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kerjasama
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kerjasama
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kerjasama
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table kerjasama
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table kerjasama
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdTipeKerjasamaIsNull() {
            addCriterion("id_tipe_kerjasama is null");
            return (Criteria) this;
        }

        public Criteria andIdTipeKerjasamaIsNotNull() {
            addCriterion("id_tipe_kerjasama is not null");
            return (Criteria) this;
        }

        public Criteria andIdTipeKerjasamaEqualTo(Integer value) {
            addCriterion("id_tipe_kerjasama =", value, "idTipeKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdTipeKerjasamaNotEqualTo(Integer value) {
            addCriterion("id_tipe_kerjasama <>", value, "idTipeKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdTipeKerjasamaGreaterThan(Integer value) {
            addCriterion("id_tipe_kerjasama >", value, "idTipeKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdTipeKerjasamaGreaterThanOrEqualTo(Integer value) {
            addCriterion("id_tipe_kerjasama >=", value, "idTipeKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdTipeKerjasamaLessThan(Integer value) {
            addCriterion("id_tipe_kerjasama <", value, "idTipeKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdTipeKerjasamaLessThanOrEqualTo(Integer value) {
            addCriterion("id_tipe_kerjasama <=", value, "idTipeKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdTipeKerjasamaIn(List<Integer> values) {
            addCriterion("id_tipe_kerjasama in", values, "idTipeKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdTipeKerjasamaNotIn(List<Integer> values) {
            addCriterion("id_tipe_kerjasama not in", values, "idTipeKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdTipeKerjasamaBetween(Integer value1, Integer value2) {
            addCriterion("id_tipe_kerjasama between", value1, value2, "idTipeKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdTipeKerjasamaNotBetween(Integer value1, Integer value2) {
            addCriterion("id_tipe_kerjasama not between", value1, value2, "idTipeKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdPihakPertamaIsNull() {
            addCriterion("id_pihak_pertama is null");
            return (Criteria) this;
        }

        public Criteria andIdPihakPertamaIsNotNull() {
            addCriterion("id_pihak_pertama is not null");
            return (Criteria) this;
        }

        public Criteria andIdPihakPertamaEqualTo(Integer value) {
            addCriterion("id_pihak_pertama =", value, "idPihakPertama");
            return (Criteria) this;
        }

        public Criteria andIdPihakPertamaNotEqualTo(Integer value) {
            addCriterion("id_pihak_pertama <>", value, "idPihakPertama");
            return (Criteria) this;
        }

        public Criteria andIdPihakPertamaGreaterThan(Integer value) {
            addCriterion("id_pihak_pertama >", value, "idPihakPertama");
            return (Criteria) this;
        }

        public Criteria andIdPihakPertamaGreaterThanOrEqualTo(Integer value) {
            addCriterion("id_pihak_pertama >=", value, "idPihakPertama");
            return (Criteria) this;
        }

        public Criteria andIdPihakPertamaLessThan(Integer value) {
            addCriterion("id_pihak_pertama <", value, "idPihakPertama");
            return (Criteria) this;
        }

        public Criteria andIdPihakPertamaLessThanOrEqualTo(Integer value) {
            addCriterion("id_pihak_pertama <=", value, "idPihakPertama");
            return (Criteria) this;
        }

        public Criteria andIdPihakPertamaIn(List<Integer> values) {
            addCriterion("id_pihak_pertama in", values, "idPihakPertama");
            return (Criteria) this;
        }

        public Criteria andIdPihakPertamaNotIn(List<Integer> values) {
            addCriterion("id_pihak_pertama not in", values, "idPihakPertama");
            return (Criteria) this;
        }

        public Criteria andIdPihakPertamaBetween(Integer value1, Integer value2) {
            addCriterion("id_pihak_pertama between", value1, value2, "idPihakPertama");
            return (Criteria) this;
        }

        public Criteria andIdPihakPertamaNotBetween(Integer value1, Integer value2) {
            addCriterion("id_pihak_pertama not between", value1, value2, "idPihakPertama");
            return (Criteria) this;
        }

        public Criteria andIdPihakKeduaIsNull() {
            addCriterion("id_pihak_kedua is null");
            return (Criteria) this;
        }

        public Criteria andIdPihakKeduaIsNotNull() {
            addCriterion("id_pihak_kedua is not null");
            return (Criteria) this;
        }

        public Criteria andIdPihakKeduaEqualTo(Integer value) {
            addCriterion("id_pihak_kedua =", value, "idPihakKedua");
            return (Criteria) this;
        }

        public Criteria andIdPihakKeduaNotEqualTo(Integer value) {
            addCriterion("id_pihak_kedua <>", value, "idPihakKedua");
            return (Criteria) this;
        }

        public Criteria andIdPihakKeduaGreaterThan(Integer value) {
            addCriterion("id_pihak_kedua >", value, "idPihakKedua");
            return (Criteria) this;
        }

        public Criteria andIdPihakKeduaGreaterThanOrEqualTo(Integer value) {
            addCriterion("id_pihak_kedua >=", value, "idPihakKedua");
            return (Criteria) this;
        }

        public Criteria andIdPihakKeduaLessThan(Integer value) {
            addCriterion("id_pihak_kedua <", value, "idPihakKedua");
            return (Criteria) this;
        }

        public Criteria andIdPihakKeduaLessThanOrEqualTo(Integer value) {
            addCriterion("id_pihak_kedua <=", value, "idPihakKedua");
            return (Criteria) this;
        }

        public Criteria andIdPihakKeduaIn(List<Integer> values) {
            addCriterion("id_pihak_kedua in", values, "idPihakKedua");
            return (Criteria) this;
        }

        public Criteria andIdPihakKeduaNotIn(List<Integer> values) {
            addCriterion("id_pihak_kedua not in", values, "idPihakKedua");
            return (Criteria) this;
        }

        public Criteria andIdPihakKeduaBetween(Integer value1, Integer value2) {
            addCriterion("id_pihak_kedua between", value1, value2, "idPihakKedua");
            return (Criteria) this;
        }

        public Criteria andIdPihakKeduaNotBetween(Integer value1, Integer value2) {
            addCriterion("id_pihak_kedua not between", value1, value2, "idPihakKedua");
            return (Criteria) this;
        }

        public Criteria andJudulIsNull() {
            addCriterion("judul is null");
            return (Criteria) this;
        }

        public Criteria andJudulIsNotNull() {
            addCriterion("judul is not null");
            return (Criteria) this;
        }

        public Criteria andJudulEqualTo(String value) {
            addCriterion("judul =", value, "judul");
            return (Criteria) this;
        }

        public Criteria andJudulNotEqualTo(String value) {
            addCriterion("judul <>", value, "judul");
            return (Criteria) this;
        }

        public Criteria andJudulGreaterThan(String value) {
            addCriterion("judul >", value, "judul");
            return (Criteria) this;
        }

        public Criteria andJudulGreaterThanOrEqualTo(String value) {
            addCriterion("judul >=", value, "judul");
            return (Criteria) this;
        }

        public Criteria andJudulLessThan(String value) {
            addCriterion("judul <", value, "judul");
            return (Criteria) this;
        }

        public Criteria andJudulLessThanOrEqualTo(String value) {
            addCriterion("judul <=", value, "judul");
            return (Criteria) this;
        }

        public Criteria andJudulLike(String value) {
            addCriterion("judul like", value, "judul");
            return (Criteria) this;
        }

        public Criteria andJudulNotLike(String value) {
            addCriterion("judul not like", value, "judul");
            return (Criteria) this;
        }

        public Criteria andJudulIn(List<String> values) {
            addCriterion("judul in", values, "judul");
            return (Criteria) this;
        }

        public Criteria andJudulNotIn(List<String> values) {
            addCriterion("judul not in", values, "judul");
            return (Criteria) this;
        }

        public Criteria andJudulBetween(String value1, String value2) {
            addCriterion("judul between", value1, value2, "judul");
            return (Criteria) this;
        }

        public Criteria andJudulNotBetween(String value1, String value2) {
            addCriterion("judul not between", value1, value2, "judul");
            return (Criteria) this;
        }

        public Criteria andDeskripsiIsNull() {
            addCriterion("deskripsi is null");
            return (Criteria) this;
        }

        public Criteria andDeskripsiIsNotNull() {
            addCriterion("deskripsi is not null");
            return (Criteria) this;
        }

        public Criteria andDeskripsiEqualTo(String value) {
            addCriterion("deskripsi =", value, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiNotEqualTo(String value) {
            addCriterion("deskripsi <>", value, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiGreaterThan(String value) {
            addCriterion("deskripsi >", value, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiGreaterThanOrEqualTo(String value) {
            addCriterion("deskripsi >=", value, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiLessThan(String value) {
            addCriterion("deskripsi <", value, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiLessThanOrEqualTo(String value) {
            addCriterion("deskripsi <=", value, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiLike(String value) {
            addCriterion("deskripsi like", value, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiNotLike(String value) {
            addCriterion("deskripsi not like", value, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiIn(List<String> values) {
            addCriterion("deskripsi in", values, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiNotIn(List<String> values) {
            addCriterion("deskripsi not in", values, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiBetween(String value1, String value2) {
            addCriterion("deskripsi between", value1, value2, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiNotBetween(String value1, String value2) {
            addCriterion("deskripsi not between", value1, value2, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andTanggalPerjanjianIsNull() {
            addCriterion("tanggal_perjanjian is null");
            return (Criteria) this;
        }

        public Criteria andTanggalPerjanjianIsNotNull() {
            addCriterion("tanggal_perjanjian is not null");
            return (Criteria) this;
        }

        public Criteria andTanggalPerjanjianEqualTo(Date value) {
            addCriterion("tanggal_perjanjian =", value, "tanggalPerjanjian");
            return (Criteria) this;
        }

        public Criteria andTanggalPerjanjianNotEqualTo(Date value) {
            addCriterion("tanggal_perjanjian <>", value, "tanggalPerjanjian");
            return (Criteria) this;
        }

        public Criteria andTanggalPerjanjianGreaterThan(Date value) {
            addCriterion("tanggal_perjanjian >", value, "tanggalPerjanjian");
            return (Criteria) this;
        }

        public Criteria andTanggalPerjanjianGreaterThanOrEqualTo(Date value) {
            addCriterion("tanggal_perjanjian >=", value, "tanggalPerjanjian");
            return (Criteria) this;
        }

        public Criteria andTanggalPerjanjianLessThan(Date value) {
            addCriterion("tanggal_perjanjian <", value, "tanggalPerjanjian");
            return (Criteria) this;
        }

        public Criteria andTanggalPerjanjianLessThanOrEqualTo(Date value) {
            addCriterion("tanggal_perjanjian <=", value, "tanggalPerjanjian");
            return (Criteria) this;
        }

        public Criteria andTanggalPerjanjianIn(List<Date> values) {
            addCriterion("tanggal_perjanjian in", values, "tanggalPerjanjian");
            return (Criteria) this;
        }

        public Criteria andTanggalPerjanjianNotIn(List<Date> values) {
            addCriterion("tanggal_perjanjian not in", values, "tanggalPerjanjian");
            return (Criteria) this;
        }

        public Criteria andTanggalPerjanjianBetween(Date value1, Date value2) {
            addCriterion("tanggal_perjanjian between", value1, value2, "tanggalPerjanjian");
            return (Criteria) this;
        }

        public Criteria andTanggalPerjanjianNotBetween(Date value1, Date value2) {
            addCriterion("tanggal_perjanjian not between", value1, value2, "tanggalPerjanjian");
            return (Criteria) this;
        }

        public Criteria andTanggalMulaiIsNull() {
            addCriterion("tanggal_mulai is null");
            return (Criteria) this;
        }

        public Criteria andTanggalMulaiIsNotNull() {
            addCriterion("tanggal_mulai is not null");
            return (Criteria) this;
        }

        public Criteria andTanggalMulaiEqualTo(Date value) {
            addCriterion("tanggal_mulai =", value, "tanggalMulai");
            return (Criteria) this;
        }

        public Criteria andTanggalMulaiNotEqualTo(Date value) {
            addCriterion("tanggal_mulai <>", value, "tanggalMulai");
            return (Criteria) this;
        }

        public Criteria andTanggalMulaiGreaterThan(Date value) {
            addCriterion("tanggal_mulai >", value, "tanggalMulai");
            return (Criteria) this;
        }

        public Criteria andTanggalMulaiGreaterThanOrEqualTo(Date value) {
            addCriterion("tanggal_mulai >=", value, "tanggalMulai");
            return (Criteria) this;
        }

        public Criteria andTanggalMulaiLessThan(Date value) {
            addCriterion("tanggal_mulai <", value, "tanggalMulai");
            return (Criteria) this;
        }

        public Criteria andTanggalMulaiLessThanOrEqualTo(Date value) {
            addCriterion("tanggal_mulai <=", value, "tanggalMulai");
            return (Criteria) this;
        }

        public Criteria andTanggalMulaiIn(List<Date> values) {
            addCriterion("tanggal_mulai in", values, "tanggalMulai");
            return (Criteria) this;
        }

        public Criteria andTanggalMulaiNotIn(List<Date> values) {
            addCriterion("tanggal_mulai not in", values, "tanggalMulai");
            return (Criteria) this;
        }

        public Criteria andTanggalMulaiBetween(Date value1, Date value2) {
            addCriterion("tanggal_mulai between", value1, value2, "tanggalMulai");
            return (Criteria) this;
        }

        public Criteria andTanggalMulaiNotBetween(Date value1, Date value2) {
            addCriterion("tanggal_mulai not between", value1, value2, "tanggalMulai");
            return (Criteria) this;
        }

        public Criteria andTanggalSelesaiIsNull() {
            addCriterion("tanggal_selesai is null");
            return (Criteria) this;
        }

        public Criteria andTanggalSelesaiIsNotNull() {
            addCriterion("tanggal_selesai is not null");
            return (Criteria) this;
        }

        public Criteria andTanggalSelesaiEqualTo(Date value) {
            addCriterion("tanggal_selesai =", value, "tanggalSelesai");
            return (Criteria) this;
        }

        public Criteria andTanggalSelesaiNotEqualTo(Date value) {
            addCriterion("tanggal_selesai <>", value, "tanggalSelesai");
            return (Criteria) this;
        }

        public Criteria andTanggalSelesaiGreaterThan(Date value) {
            addCriterion("tanggal_selesai >", value, "tanggalSelesai");
            return (Criteria) this;
        }

        public Criteria andTanggalSelesaiGreaterThanOrEqualTo(Date value) {
            addCriterion("tanggal_selesai >=", value, "tanggalSelesai");
            return (Criteria) this;
        }

        public Criteria andTanggalSelesaiLessThan(Date value) {
            addCriterion("tanggal_selesai <", value, "tanggalSelesai");
            return (Criteria) this;
        }

        public Criteria andTanggalSelesaiLessThanOrEqualTo(Date value) {
            addCriterion("tanggal_selesai <=", value, "tanggalSelesai");
            return (Criteria) this;
        }

        public Criteria andTanggalSelesaiIn(List<Date> values) {
            addCriterion("tanggal_selesai in", values, "tanggalSelesai");
            return (Criteria) this;
        }

        public Criteria andTanggalSelesaiNotIn(List<Date> values) {
            addCriterion("tanggal_selesai not in", values, "tanggalSelesai");
            return (Criteria) this;
        }

        public Criteria andTanggalSelesaiBetween(Date value1, Date value2) {
            addCriterion("tanggal_selesai between", value1, value2, "tanggalSelesai");
            return (Criteria) this;
        }

        public Criteria andTanggalSelesaiNotBetween(Date value1, Date value2) {
            addCriterion("tanggal_selesai not between", value1, value2, "tanggalSelesai");
            return (Criteria) this;
        }

        public Criteria andJudulLikeInsensitive(String value) {
            addCriterion("upper(judul) like", value.toUpperCase(), "judul");
            return (Criteria) this;
        }

        public Criteria andDeskripsiLikeInsensitive(String value) {
            addCriterion("upper(deskripsi) like", value.toUpperCase(), "deskripsi");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table kerjasama
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table kerjasama
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}