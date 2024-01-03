package model.service.license;

import model.DAO.DAOLicense;
import org.junit.jupiter.api.Test;
import model.entity.License;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LicenseActivationTest {

    @Test
    void testGetLicense() {
        // Mock the DAOLicense class
        DAOLicense daoLicenseMock = mock(DAOLicense.class);

        // Mock a License instance
        License mockLicense = mock(License.class);

        // Mock the behavior of getLicenseByCode()
        when(daoLicenseMock.getLicenseByCode(anyString())).thenReturn(mockLicense);

        // Initialize LicenseActivation with mocked DAOLicense
        LicenseActivation licenseActivation = new LicenseActivation(daoLicenseMock);

        // Test getLicense()
        License license = licenseActivation.getLicense("test code");
        assertNotNull(license);
        assertEquals(mockLicense, license);

        // Verify interaction with mock
        verify(daoLicenseMock).getLicenseByCode("test code");
    }

    @Test
    void testIsActivable() {
        // Mock a License instance
        License mockLicense = mock(License.class);

        // Initialize LicenseActivation with null as we are not interacting with DAO in this test
        LicenseActivation licenseActivation = new LicenseActivation(null);

        // Mock the behavior of isActive() for return false
        when(mockLicense.isActive()).thenReturn(false);

        // Test isActivable()
        boolean isActivable = licenseActivation.isActivable(mockLicense);
        assertTrue(isActivable);

        // Verify interaction with mock
        verify(mockLicense).isActive();
    }

    @Test
    void testIsForTherapist() {
        // Initialize LicenseActivation with null as we are not interacting with DAO in this test
        LicenseActivation licenseActivation = new LicenseActivation(null);

        // Mock a License instance
        License mockLicense = mock(License.class);

        // Test isForTherapist method when sequence length is not 4 (expecting 0 as return)
        when(mockLicense.getSequence()).thenReturn("123");
        assertEquals(0, licenseActivation.isForTherapist(mockLicense));

        // Test with sequence length equal to 4
        when(mockLicense.getSequence()).thenReturn("1234");
        when(mockLicense.getIdUser()).thenReturn(9);
        assertEquals(9, licenseActivation.isForTherapist(mockLicense));
    }

    @Test
    void testActivate() {
        // Mock the DAOLicense class
        DAOLicense daoLicenseMock = mock(DAOLicense.class);

        // Mock a License instance
        License mockLicense = mock(License.class);

        // Initialize LicenseActivation with mocked DAOLicense
        LicenseActivation licenseActivation = new LicenseActivation(daoLicenseMock);

        // Test activate()
        licenseActivation.activate(mockLicense, 1);

        // Verify interaction with mock
        verify(daoLicenseMock).activate(mockLicense, 1);
    }

    @Test
    void testGeneratePinAndLicense() {
        // Mock the DAOLicense class
        DAOLicense daoLicenseMock = mock(DAOLicense.class);

        String samplePin = "1234";
        String sampleLicense = "12345678";

        // Mock the behavior of generateInvitation() and generateLicense()
        when(daoLicenseMock.generateInvitation(1)).thenReturn(samplePin);
        when(daoLicenseMock.generateLicense()).thenReturn(sampleLicense);

        // Initialize LicenseActivation with mocked DAOLicense
        LicenseActivation licenseActivation = new LicenseActivation(daoLicenseMock);

        // Test generatePin()
        String generatedPin = licenseActivation.generatePin(1);
        assertEquals(samplePin, generatedPin);
        verify(daoLicenseMock).generateInvitation(1);

        // Test generateLicense()
        String generatedLicense = licenseActivation.generateLicense();
        assertEquals(sampleLicense, generatedLicense);
        verify(daoLicenseMock).generateLicense();
    }
}