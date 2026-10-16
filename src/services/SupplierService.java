package services;

import java.util.ArrayList;

import models.Supplier;

public class SupplierService {

    private ArrayList<Supplier> suppliers;

    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public SupplierService() {

        suppliers = new ArrayList<>();
    }

    // ==========================================
    // ADD SUPPLIER
    // ==========================================

    public boolean addSupplier(
            Supplier supplier) {

        if (supplier == null) {
            return false;
        }

        // Supplier ID must be unique
        if (findSupplierById(
                supplier.getSupplierId()) != null) {

            return false;
        }

        suppliers.add(supplier);

        return true;
    }

    // ==========================================
    // VIEW SUPPLIERS
    // ==========================================

    public void displaySuppliers() {

        if (suppliers.isEmpty()) {

            System.out.println(
                    "No suppliers available.");

            return;
        }

        System.out.println();
        System.out.println(
                "----------- SUPPLIER LIST -----------");

        for (Supplier supplier : suppliers) {

            System.out.println(supplier);
        }
    }

    // ==========================================
    // FIND SUPPLIER BY ID
    // ==========================================

    public Supplier findSupplierById(
            int supplierId) {

        for (Supplier supplier : suppliers) {

            if (supplier.getSupplierId()
                    == supplierId) {

                return supplier;
            }
        }

        return null;
    }

    // ==========================================
    // UPDATE SUPPLIER
    // ==========================================

    public boolean updateSupplier(
            int supplierId,
            String supplierName,
            String location,
            String contactNumber) {

        Supplier supplier =
                findSupplierById(supplierId);

        if (supplier == null) {
            return false;
        }

        supplier.setSupplierName(
                supplierName);

        supplier.setLocation(
                location);

        supplier.setContactNumber(
                contactNumber);

        return true;
    }

    // ==========================================
    // DELETE SUPPLIER
    // ==========================================

    public boolean deleteSupplier(
            int supplierId) {

        Supplier supplier =
                findSupplierById(supplierId);

        if (supplier == null) {
            return false;
        }

        suppliers.remove(supplier);

        return true;
    }

    // ==========================================
    // CHECK SUPPLIER EXISTS
    // ==========================================

    public boolean supplierExists(
            int supplierId) {

        return findSupplierById(
                supplierId) != null;
    }

    // ==========================================
    // GET ALL SUPPLIERS
    // ==========================================

    public ArrayList<Supplier> getSuppliers() {

        return suppliers;
    }

    // ==========================================
    // GET SUPPLIER COUNT
    // ==========================================

    public int getSupplierCount() {

        return suppliers.size();
    }
}