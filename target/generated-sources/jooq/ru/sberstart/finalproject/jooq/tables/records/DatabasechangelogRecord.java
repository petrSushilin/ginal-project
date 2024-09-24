/*
 * This file is generated by jOOQ.
 */
package ru.sberstart.finalproject.jooq.tables.records;


import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record14;
import org.jooq.Row14;
import org.jooq.impl.TableRecordImpl;

import ru.sberstart.finalproject.jooq.tables.Databasechangelog;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DatabasechangelogRecord extends TableRecordImpl<DatabasechangelogRecord> implements Record14<String, String, String, LocalDateTime, Integer, String, String, String, String, String, String, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.databasechangelog.id</code>.
     */
    public void setId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.databasechangelog.id</code>.
     */
    public String getId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.databasechangelog.author</code>.
     */
    public void setAuthor(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.databasechangelog.author</code>.
     */
    public String getAuthor() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.databasechangelog.filename</code>.
     */
    public void setFilename(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.databasechangelog.filename</code>.
     */
    public String getFilename() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.databasechangelog.dateexecuted</code>.
     */
    public void setDateexecuted(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.databasechangelog.dateexecuted</code>.
     */
    public LocalDateTime getDateexecuted() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>public.databasechangelog.orderexecuted</code>.
     */
    public void setOrderexecuted(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.databasechangelog.orderexecuted</code>.
     */
    public Integer getOrderexecuted() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>public.databasechangelog.exectype</code>.
     */
    public void setExectype(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.databasechangelog.exectype</code>.
     */
    public String getExectype() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.databasechangelog.md5sum</code>.
     */
    public void setMd5sum(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.databasechangelog.md5sum</code>.
     */
    public String getMd5sum() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.databasechangelog.description</code>.
     */
    public void setDescription(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.databasechangelog.description</code>.
     */
    public String getDescription() {
        return (String) get(7);
    }

    /**
     * Setter for <code>public.databasechangelog.comments</code>.
     */
    public void setComments(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.databasechangelog.comments</code>.
     */
    public String getComments() {
        return (String) get(8);
    }

    /**
     * Setter for <code>public.databasechangelog.tag</code>.
     */
    public void setTag(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.databasechangelog.tag</code>.
     */
    public String getTag() {
        return (String) get(9);
    }

    /**
     * Setter for <code>public.databasechangelog.liquibase</code>.
     */
    public void setLiquibase(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.databasechangelog.liquibase</code>.
     */
    public String getLiquibase() {
        return (String) get(10);
    }

    /**
     * Setter for <code>public.databasechangelog.contexts</code>.
     */
    public void setContexts(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>public.databasechangelog.contexts</code>.
     */
    public String getContexts() {
        return (String) get(11);
    }

    /**
     * Setter for <code>public.databasechangelog.labels</code>.
     */
    public void setLabels(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>public.databasechangelog.labels</code>.
     */
    public String getLabels() {
        return (String) get(12);
    }

    /**
     * Setter for <code>public.databasechangelog.deployment_id</code>.
     */
    public void setDeploymentId(String value) {
        set(13, value);
    }

    /**
     * Getter for <code>public.databasechangelog.deployment_id</code>.
     */
    public String getDeploymentId() {
        return (String) get(13);
    }

    // -------------------------------------------------------------------------
    // Record14 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row14<String, String, String, LocalDateTime, Integer, String, String, String, String, String, String, String, String, String> fieldsRow() {
        return (Row14) super.fieldsRow();
    }

    @Override
    public Row14<String, String, String, LocalDateTime, Integer, String, String, String, String, String, String, String, String, String> valuesRow() {
        return (Row14) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Databasechangelog.DATABASECHANGELOG.ID;
    }

    @Override
    public Field<String> field2() {
        return Databasechangelog.DATABASECHANGELOG.AUTHOR;
    }

    @Override
    public Field<String> field3() {
        return Databasechangelog.DATABASECHANGELOG.FILENAME;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return Databasechangelog.DATABASECHANGELOG.DATEEXECUTED;
    }

    @Override
    public Field<Integer> field5() {
        return Databasechangelog.DATABASECHANGELOG.ORDEREXECUTED;
    }

    @Override
    public Field<String> field6() {
        return Databasechangelog.DATABASECHANGELOG.EXECTYPE;
    }

    @Override
    public Field<String> field7() {
        return Databasechangelog.DATABASECHANGELOG.MD5SUM;
    }

    @Override
    public Field<String> field8() {
        return Databasechangelog.DATABASECHANGELOG.DESCRIPTION;
    }

    @Override
    public Field<String> field9() {
        return Databasechangelog.DATABASECHANGELOG.COMMENTS;
    }

    @Override
    public Field<String> field10() {
        return Databasechangelog.DATABASECHANGELOG.TAG;
    }

    @Override
    public Field<String> field11() {
        return Databasechangelog.DATABASECHANGELOG.LIQUIBASE;
    }

    @Override
    public Field<String> field12() {
        return Databasechangelog.DATABASECHANGELOG.CONTEXTS;
    }

    @Override
    public Field<String> field13() {
        return Databasechangelog.DATABASECHANGELOG.LABELS;
    }

    @Override
    public Field<String> field14() {
        return Databasechangelog.DATABASECHANGELOG.DEPLOYMENT_ID;
    }

    @Override
    public String component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getAuthor();
    }

    @Override
    public String component3() {
        return getFilename();
    }

    @Override
    public LocalDateTime component4() {
        return getDateexecuted();
    }

    @Override
    public Integer component5() {
        return getOrderexecuted();
    }

    @Override
    public String component6() {
        return getExectype();
    }

    @Override
    public String component7() {
        return getMd5sum();
    }

    @Override
    public String component8() {
        return getDescription();
    }

    @Override
    public String component9() {
        return getComments();
    }

    @Override
    public String component10() {
        return getTag();
    }

    @Override
    public String component11() {
        return getLiquibase();
    }

    @Override
    public String component12() {
        return getContexts();
    }

    @Override
    public String component13() {
        return getLabels();
    }

    @Override
    public String component14() {
        return getDeploymentId();
    }

    @Override
    public String value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getAuthor();
    }

    @Override
    public String value3() {
        return getFilename();
    }

    @Override
    public LocalDateTime value4() {
        return getDateexecuted();
    }

    @Override
    public Integer value5() {
        return getOrderexecuted();
    }

    @Override
    public String value6() {
        return getExectype();
    }

    @Override
    public String value7() {
        return getMd5sum();
    }

    @Override
    public String value8() {
        return getDescription();
    }

    @Override
    public String value9() {
        return getComments();
    }

    @Override
    public String value10() {
        return getTag();
    }

    @Override
    public String value11() {
        return getLiquibase();
    }

    @Override
    public String value12() {
        return getContexts();
    }

    @Override
    public String value13() {
        return getLabels();
    }

    @Override
    public String value14() {
        return getDeploymentId();
    }

    @Override
    public DatabasechangelogRecord value1(String value) {
        setId(value);
        return this;
    }

    @Override
    public DatabasechangelogRecord value2(String value) {
        setAuthor(value);
        return this;
    }

    @Override
    public DatabasechangelogRecord value3(String value) {
        setFilename(value);
        return this;
    }

    @Override
    public DatabasechangelogRecord value4(LocalDateTime value) {
        setDateexecuted(value);
        return this;
    }

    @Override
    public DatabasechangelogRecord value5(Integer value) {
        setOrderexecuted(value);
        return this;
    }

    @Override
    public DatabasechangelogRecord value6(String value) {
        setExectype(value);
        return this;
    }

    @Override
    public DatabasechangelogRecord value7(String value) {
        setMd5sum(value);
        return this;
    }

    @Override
    public DatabasechangelogRecord value8(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public DatabasechangelogRecord value9(String value) {
        setComments(value);
        return this;
    }

    @Override
    public DatabasechangelogRecord value10(String value) {
        setTag(value);
        return this;
    }

    @Override
    public DatabasechangelogRecord value11(String value) {
        setLiquibase(value);
        return this;
    }

    @Override
    public DatabasechangelogRecord value12(String value) {
        setContexts(value);
        return this;
    }

    @Override
    public DatabasechangelogRecord value13(String value) {
        setLabels(value);
        return this;
    }

    @Override
    public DatabasechangelogRecord value14(String value) {
        setDeploymentId(value);
        return this;
    }

    @Override
    public DatabasechangelogRecord values(String value1, String value2, String value3, LocalDateTime value4, Integer value5, String value6, String value7, String value8, String value9, String value10, String value11, String value12, String value13, String value14) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DatabasechangelogRecord
     */
    public DatabasechangelogRecord() {
        super(Databasechangelog.DATABASECHANGELOG);
    }

    /**
     * Create a detached, initialised DatabasechangelogRecord
     */
    public DatabasechangelogRecord(String id, String author, String filename, LocalDateTime dateexecuted, Integer orderexecuted, String exectype, String md5sum, String description, String comments, String tag, String liquibase, String contexts, String labels, String deploymentId) {
        super(Databasechangelog.DATABASECHANGELOG);

        setId(id);
        setAuthor(author);
        setFilename(filename);
        setDateexecuted(dateexecuted);
        setOrderexecuted(orderexecuted);
        setExectype(exectype);
        setMd5sum(md5sum);
        setDescription(description);
        setComments(comments);
        setTag(tag);
        setLiquibase(liquibase);
        setContexts(contexts);
        setLabels(labels);
        setDeploymentId(deploymentId);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised DatabasechangelogRecord
     */
    public DatabasechangelogRecord(ru.sberstart.finalproject.jooq.tables.pojos.Databasechangelog value) {
        super(Databasechangelog.DATABASECHANGELOG);

        if (value != null) {
            setId(value.getId());
            setAuthor(value.getAuthor());
            setFilename(value.getFilename());
            setDateexecuted(value.getDateexecuted());
            setOrderexecuted(value.getOrderexecuted());
            setExectype(value.getExectype());
            setMd5sum(value.getMd5sum());
            setDescription(value.getDescription());
            setComments(value.getComments());
            setTag(value.getTag());
            setLiquibase(value.getLiquibase());
            setContexts(value.getContexts());
            setLabels(value.getLabels());
            setDeploymentId(value.getDeploymentId());
            resetChangedOnNotNull();
        }
    }
}
