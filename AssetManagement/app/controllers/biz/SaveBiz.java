package controllers.biz;

import models.*;

import java.util.Date;

public class SaveBiz {

    public static void beforeSave(Admin obj) {

    }

    public static void beforeUpdate(Admin obj) {
        obj.lastUpdateTime = new Date();
    }

    public static void beforeSave(AdminJournal obj) {

    }

    public static void beforeUpdate(AdminJournal obj) {
    }

    public static void beforeSave(AssetCard obj) {

    }

    public static void beforeUpdate(AssetCard obj) {
        obj.lastUpdateTime = new Date();
    }

    public static void beforeSave(AssetHandle obj) {

    }

    public static void beforeUpdate(AssetHandle obj) {
        obj.lastUpdateTime = new Date();
    }

    public static void beforeSave(AssetType obj) {

    }

    public static void beforeUpdate(AssetType obj) {
        obj.lastUpdateTime = new Date();
    }

    public static void beforeSave(AssetUse obj) {

    }

    public static void beforeUpdate(AssetUse obj) {
        obj.lastUpdateTime = new Date();
    }

    public static void beforeSave(Auth obj) {

    }

    public static void beforeUpdate(Auth obj) {
        obj.lastUpdateTime = new Date();
    }

    public static void beforeSave(Barcode obj) {

    }

    public static void beforeUpdate(Barcode obj) {
        obj.lastUpdateTime = new Date();
    }

    public static void beforeSave(Config obj) {

    }

    public static void beforeUpdate(Config obj) {
        obj.lastUpdateTime = new Date();
    }

    public static void beforeSave(Depart obj) {

    }

    public static void beforeUpdate(Depart obj) {
        obj.lastUpdateTime = new Date();
    }

    public static void beforeSave(Dict obj) {

    }

    public static void beforeUpdate(Dict obj) {
        obj.lastUpdateTime = new Date();
    }

    public static void beforeSave(FixRecord obj) {

    }

    public static void beforeUpdate(FixRecord obj) {
        obj.lastUpdateTime = new Date();
    }

    public static void beforeSave(FixRequest obj) {

    }

    public static void beforeUpdate(FixRequest obj) {
        obj.lastUpdateTime = new Date();
    }

    public static void beforeSave(Location obj) {

    }

    public static void beforeUpdate(Location obj) {
        obj.lastUpdateTime = new Date();
    }

    public static void beforeSave(Material obj) {

    }

    public static void beforeUpdate(Material obj) {
        obj.lastUpdateTime = new Date();
    }

    public static void beforeSave(Member obj) {

    }

    public static void beforeUpdate(Member obj) {
        obj.lastUpdateTime = new Date();
    }

    public static void beforeSave(Purchase obj) {

    }

    public static void beforeUpdate(Purchase obj) {
        obj.lastUpdateTime = new Date();
    }

    public static void beforeSave(User obj) {

    }

    public static void beforeUpdate(User obj) {
        obj.lastUpdateTime = new Date();
    }

    public static void beforeSave(UseRequest obj) {

    }

    public static void beforeUpdate(UseRequest obj) {
        obj.lastUpdateTime = new Date();
    }
}