package model.service.license;

import model.DAO.DAOLicense;
import org.junit.jupiter.api.Test;
import model.service.license.LicenseActivation;
import model.entity.License;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LicenseActivationTest {

    @Test
    void testGetLicense() {
        // Mock the DAOLicense class
        DAOLicense daoLicenseMock = mock(DAOLicense.class);

        // Set up the LicenseActivation class with the mocked DAO
        LicenseActivation licenseActivation = new LicenseActivation();
        licenseActivation.setDAOLicense(daoLicenseMock);

        // Create a sample license
        License sampleLicense = new License();
        sampleLicense.setSequence("1234");
        sampleLicense.setIdUser(1);

        // Mock the behavior of the DAO method
        when(daoLicenseMock.getLicenseByCode(anyString())).thenReturn(sampleLicense);

        // Test the getLicense method
        License resultLicense = licenseActivation.getLicense("sampleCode");
        assertNotNull(resultLicense);
        assertEquals(sampleLicense, resultLicense);
    }

    @Test
    void testIsActivable() {
        LicenseActivation licenseActivation = new LicenseActivation();

        // Test with an active license
        License activeLicense = new License();
        activeLicense.setActive(true);
        assertFalse(licenseActivation.isActivable(activeLicense));

        // Test with an inactive license
        License inactiveLicense = new License();
        inactiveLicense.setActive(false);
        assertTrue(licenseActivation.isActivable(inactiveLicense));

        // Test with a null license
        assertFalse(licenseActivation.isActivable(null));
    }

    @Test
    void testIsForTherapist() {
        LicenseActivation licenseActivation = new LicenseActivation();

        // Test with a valid therapist license
        License therapistLicense = new License();
        therapistLicense.setSequence("1234");
        therapistLicense.setIdUser(1);
        assertEquals(1, licenseActivation.isForTherapist(therapistLicense));

        // Test with an invalid therapist license
        License invalidTherapistLicense = new License();
        invalidTherapistLicense.setSequence("123");
        invalidTherapistLicense.setIdUser(2);
        assertEquals(0, licenseActivation.isForTherapist(invalidTherapistLicense));

        // Test with a null license
        assertEquals(0, licenseActivation.isForTherapist(null));
    }


    @Test
    void testGeneratingLicenses(){
        DAOLicense daoLicense = new DAOLicense();

        LicenseActivation licenseActivation = new LicenseActivation();
        String licenseCode = licenseActivation.generateLicense();
        String pinCode = licenseActivation.generatePin(9);

        assertEquals(8, licenseCode.length());
        assertEquals(4, pinCode.length());

        licenseActivation.activate(daoLicense.getLicenseByCode(licenseCode), 0);
        licenseActivation.activate(daoLicense.getLicenseByCode(pinCode), 999);

        License license1 = daoLicense.getLicenseByCode(licenseCode);
        assertTrue(license1.isActive());
        assertEquals(0, license1.getIdUser());

        License license2 = daoLicense.getLicenseByCode(pinCode);
        assertTrue(license2.isActive());
        assertEquals(999, license2.getIdUser());

        daoLicense.deleteLicense(licenseCode);
        daoLicense.deleteLicense(pinCode);
    }
}
