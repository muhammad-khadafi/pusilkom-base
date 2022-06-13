package com.pusilkom.base.model;

import java.util.ArrayList;
import java.util.List;

public class DokumenKerjasamaExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dokumen_kerjasama
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dokumen_kerjasama
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dokumen_kerjasama
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dokumen_kerjasama
     *
     * @mbg.generated
     */
    public DokumenKerjasamaExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dokumen_kerjasama
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dokumen_kerjasama
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dokumen_kerjasama
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dokumen_kerjasama
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dokumen_kerjasama
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dokumen_kerjasama
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dokumen_kerjasama
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
     * This method corresponds to the database table dokumen_kerjasama
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
     * This method corresponds to the database table dokumen_kerjasama
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dokumen_kerjasama
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
     * This class corresponds to the database table dokumen_kerjasama
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

        public Criteria andIdKerjasamaIsNull() {
            addCriterion("id_kerjasama is null");
            return (Criteria) this;
        }

        public Criteria andIdKerjasamaIsNotNull() {
            addCriterion("id_kerjasama is not null");
            return (Criteria) this;
        }

        public Criteria andIdKerjasamaEqualTo(Integer value) {
            addCriterion("id_kerjasama =", value, "idKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdKerjasamaNotEqualTo(Integer value) {
            addCriterion("id_kerjasama <>", value, "idKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdKerjasamaGreaterThan(Integer value) {
            addCriterion("id_kerjasama >", value, "idKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdKerjasamaGreaterThanOrEqualTo(Integer value) {
            addCriterion("id_kerjasama >=", value, "idKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdKerjasamaLessThan(Integer value) {
            addCriterion("id_kerjasama <", value, "idKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdKerjasamaLessThanOrEqualTo(Integer value) {
            addCriterion("id_kerjasama <=", value, "idKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdKerjasamaIn(List<Integer> values) {
            addCriterion("id_kerjasama in", values, "idKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdKerjasamaNotIn(List<Integer> values) {
            addCriterion("id_kerjasama not in", values, "idKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdKerjasamaBetween(Integer value1, Integer value2) {
            addCriterion("id_kerjasama between", value1, value2, "idKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdKerjasamaNotBetween(Integer value1, Integer value2) {
            addCriterion("id_kerjasama not between", value1, value2, "idKerjasama");
            return (Criteria) this;
        }

        public Criteria andIdTipeDokumenIsNull() {
            addCriterion("id_tipe_dokumen is null");
            return (Criteria) this;
        }

        public Criteria andIdTipeDokumenIsNotNull() {
            addCriterion("id_tipe_dokumen is not null");
            return (Criteria) this;
        }

        public Criteria andIdTipeDokumenEqualTo(Integer value) {
            addCriterion("id_tipe_dokumen =", value, "idTipeDokumen");
            return (Criteria) this;
        }

        public Criteria andIdTipeDokumenNotEqualTo(Integer value) {
            addCriterion("id_tipe_dokumen <>", value, "idTipeDokumen");
            return (Criteria) this;
        }

        public Criteria andIdTipeDokumenGreaterThan(Integer value) {
            addCriterion("id_tipe_dokumen >", value, "idTipeDokumen");
            return (Criteria) this;
        }

        public Criteria andIdTipeDokumenGreaterThanOrEqualTo(Integer value) {
            addCriterion("id_tipe_dokumen >=", value, "idTipeDokumen");
            return (Criteria) this;
        }

        public Criteria andIdTipeDokumenLessThan(Integer value) {
            addCriterion("id_tipe_dokumen <", value, "idTipeDokumen");
            return (Criteria) this;
        }

        public Criteria andIdTipeDokumenLessThanOrEqualTo(Integer value) {
            addCriterion("id_tipe_dokumen <=", value, "idTipeDokumen");
            return (Criteria) this;
        }

        public Criteria andIdTipeDokumenIn(List<Integer> values) {
            addCriterion("id_tipe_dokumen in", values, "idTipeDokumen");
            return (Criteria) this;
        }

        public Criteria andIdTipeDokumenNotIn(List<Integer> values) {
            addCriterion("id_tipe_dokumen not in", values, "idTipeDokumen");
            return (Criteria) this;
        }

        public Criteria andIdTipeDokumenBetween(Integer value1, Integer value2) {
            addCriterion("id_tipe_dokumen between", value1, value2, "idTipeDokumen");
            return (Criteria) this;
        }

        public Criteria andIdTipeDokumenNotBetween(Integer value1, Integer value2) {
            addCriterion("id_tipe_dokumen not between", value1, value2, "idTipeDokumen");
            return (Criteria) this;
        }

        public Criteria andNamaDokumenIsNull() {
            addCriterion("nama_dokumen is null");
            return (Criteria) this;
        }

        public Criteria andNamaDokumenIsNotNull() {
            addCriterion("nama_dokumen is not null");
            return (Criteria) this;
        }

        public Criteria andNamaDokumenEqualTo(String value) {
            addCriterion("nama_dokumen =", value, "namaDokumen");
            return (Criteria) this;
        }

        public Criteria andNamaDokumenNotEqualTo(String value) {
            addCriterion("nama_dokumen <>", value, "namaDokumen");
            return (Criteria) this;
        }

        public Criteria andNamaDokumenGreaterThan(String value) {
            addCriterion("nama_dokumen >", value, "namaDokumen");
            return (Criteria) this;
        }

        public Criteria andNamaDokumenGreaterThanOrEqualTo(String value) {
            addCriterion("nama_dokumen >=", value, "namaDokumen");
            return (Criteria) this;
        }

        public Criteria andNamaDokumenLessThan(String value) {
            addCriterion("nama_dokumen <", value, "namaDokumen");
            return (Criteria) this;
        }

        public Criteria andNamaDokumenLessThanOrEqualTo(String value) {
            addCriterion("nama_dokumen <=", value, "namaDokumen");
            return (Criteria) this;
        }

        public Criteria andNamaDokumenLike(String value) {
            addCriterion("nama_dokumen like", value, "namaDokumen");
            return (Criteria) this;
        }

        public Criteria andNamaDokumenNotLike(String value) {
            addCriterion("nama_dokumen not like", value, "namaDokumen");
            return (Criteria) this;
        }

        public Criteria andNamaDokumenIn(List<String> values) {
            addCriterion("nama_dokumen in", values, "namaDokumen");
            return (Criteria) this;
        }

        public Criteria andNamaDokumenNotIn(List<String> values) {
            addCriterion("nama_dokumen not in", values, "namaDokumen");
            return (Criteria) this;
        }

        public Criteria andNamaDokumenBetween(String value1, String value2) {
            addCriterion("nama_dokumen between", value1, value2, "namaDokumen");
            return (Criteria) this;
        }

        public Criteria andNamaDokumenNotBetween(String value1, String value2) {
            addCriterion("nama_dokumen not between", value1, value2, "namaDokumen");
            return (Criteria) this;
        }

        public Criteria andNamaFileIsNull() {
            addCriterion("nama_file is null");
            return (Criteria) this;
        }

        public Criteria andNamaFileIsNotNull() {
            addCriterion("nama_file is not null");
            return (Criteria) this;
        }

        public Criteria andNamaFileEqualTo(String value) {
            addCriterion("nama_file =", value, "namaFile");
            return (Criteria) this;
        }

        public Criteria andNamaFileNotEqualTo(String value) {
            addCriterion("nama_file <>", value, "namaFile");
            return (Criteria) this;
        }

        public Criteria andNamaFileGreaterThan(String value) {
            addCriterion("nama_file >", value, "namaFile");
            return (Criteria) this;
        }

        public Criteria andNamaFileGreaterThanOrEqualTo(String value) {
            addCriterion("nama_file >=", value, "namaFile");
            return (Criteria) this;
        }

        public Criteria andNamaFileLessThan(String value) {
            addCriterion("nama_file <", value, "namaFile");
            return (Criteria) this;
        }

        public Criteria andNamaFileLessThanOrEqualTo(String value) {
            addCriterion("nama_file <=", value, "namaFile");
            return (Criteria) this;
        }

        public Criteria andNamaFileLike(String value) {
            addCriterion("nama_file like", value, "namaFile");
            return (Criteria) this;
        }

        public Criteria andNamaFileNotLike(String value) {
            addCriterion("nama_file not like", value, "namaFile");
            return (Criteria) this;
        }

        public Criteria andNamaFileIn(List<String> values) {
            addCriterion("nama_file in", values, "namaFile");
            return (Criteria) this;
        }

        public Criteria andNamaFileNotIn(List<String> values) {
            addCriterion("nama_file not in", values, "namaFile");
            return (Criteria) this;
        }

        public Criteria andNamaFileBetween(String value1, String value2) {
            addCriterion("nama_file between", value1, value2, "namaFile");
            return (Criteria) this;
        }

        public Criteria andNamaFileNotBetween(String value1, String value2) {
            addCriterion("nama_file not between", value1, value2, "namaFile");
            return (Criteria) this;
        }

        public Criteria andTahunDokumenIsNull() {
            addCriterion("tahun_dokumen is null");
            return (Criteria) this;
        }

        public Criteria andTahunDokumenIsNotNull() {
            addCriterion("tahun_dokumen is not null");
            return (Criteria) this;
        }

        public Criteria andTahunDokumenEqualTo(String value) {
            addCriterion("tahun_dokumen =", value, "tahunDokumen");
            return (Criteria) this;
        }

        public Criteria andTahunDokumenNotEqualTo(String value) {
            addCriterion("tahun_dokumen <>", value, "tahunDokumen");
            return (Criteria) this;
        }

        public Criteria andTahunDokumenGreaterThan(String value) {
            addCriterion("tahun_dokumen >", value, "tahunDokumen");
            return (Criteria) this;
        }

        public Criteria andTahunDokumenGreaterThanOrEqualTo(String value) {
            addCriterion("tahun_dokumen >=", value, "tahunDokumen");
            return (Criteria) this;
        }

        public Criteria andTahunDokumenLessThan(String value) {
            addCriterion("tahun_dokumen <", value, "tahunDokumen");
            return (Criteria) this;
        }

        public Criteria andTahunDokumenLessThanOrEqualTo(String value) {
            addCriterion("tahun_dokumen <=", value, "tahunDokumen");
            return (Criteria) this;
        }

        public Criteria andTahunDokumenLike(String value) {
            addCriterion("tahun_dokumen like", value, "tahunDokumen");
            return (Criteria) this;
        }

        public Criteria andTahunDokumenNotLike(String value) {
            addCriterion("tahun_dokumen not like", value, "tahunDokumen");
            return (Criteria) this;
        }

        public Criteria andTahunDokumenIn(List<String> values) {
            addCriterion("tahun_dokumen in", values, "tahunDokumen");
            return (Criteria) this;
        }

        public Criteria andTahunDokumenNotIn(List<String> values) {
            addCriterion("tahun_dokumen not in", values, "tahunDokumen");
            return (Criteria) this;
        }

        public Criteria andTahunDokumenBetween(String value1, String value2) {
            addCriterion("tahun_dokumen between", value1, value2, "tahunDokumen");
            return (Criteria) this;
        }

        public Criteria andTahunDokumenNotBetween(String value1, String value2) {
            addCriterion("tahun_dokumen not between", value1, value2, "tahunDokumen");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNull() {
            addCriterion("file_path is null");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNotNull() {
            addCriterion("file_path is not null");
            return (Criteria) this;
        }

        public Criteria andFilePathEqualTo(String value) {
            addCriterion("file_path =", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotEqualTo(String value) {
            addCriterion("file_path <>", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThan(String value) {
            addCriterion("file_path >", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("file_path >=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThan(String value) {
            addCriterion("file_path <", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThanOrEqualTo(String value) {
            addCriterion("file_path <=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLike(String value) {
            addCriterion("file_path like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotLike(String value) {
            addCriterion("file_path not like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathIn(List<String> values) {
            addCriterion("file_path in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotIn(List<String> values) {
            addCriterion("file_path not in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathBetween(String value1, String value2) {
            addCriterion("file_path between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotBetween(String value1, String value2) {
            addCriterion("file_path not between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andNamaDokumenLikeInsensitive(String value) {
            addCriterion("upper(nama_dokumen) like", value.toUpperCase(), "namaDokumen");
            return (Criteria) this;
        }

        public Criteria andNamaFileLikeInsensitive(String value) {
            addCriterion("upper(nama_file) like", value.toUpperCase(), "namaFile");
            return (Criteria) this;
        }

        public Criteria andTahunDokumenLikeInsensitive(String value) {
            addCriterion("upper(tahun_dokumen) like", value.toUpperCase(), "tahunDokumen");
            return (Criteria) this;
        }

        public Criteria andFilePathLikeInsensitive(String value) {
            addCriterion("upper(file_path) like", value.toUpperCase(), "filePath");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table dokumen_kerjasama
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
     * This class corresponds to the database table dokumen_kerjasama
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