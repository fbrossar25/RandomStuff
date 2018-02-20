package utils;

import java.util.ArrayList;
import java.util.List;

import com.aparapi.Kernel;
import com.aparapi.device.Device;
import com.aparapi.device.OpenCLDevice;
import com.aparapi.internal.kernel.KernelManager;
import com.aparapi.internal.opencl.OpenCLPlatform;

public final class AparapiUtilities {
    private AparapiUtilities() {
        // classe utilitaire
    }

    public static List<OpenCLDevice> getAllOpenCLDevices() {
        List<OpenCLDevice> devices = new ArrayList<>();
        for (OpenCLPlatform platform : getAllOpenCLPlatforms()) {
            devices.addAll(platform.getOpenCLDevices());
        }
        return devices;
    }

    public static List<OpenCLPlatform> getAllOpenCLPlatforms() {
        return new OpenCLPlatform().getOpenCLPlatforms();
    }

    public static List<Device> getAllDevices() {
        List<Device> devices = new ArrayList<>();
        for (Device.TYPE type : Device.TYPE.values()) {
            devices.addAll(OpenCLDevice.listDevices(type));
        }
        return devices;
    }

    public static List<Device> getGPUs() {
        return getDevices(Device.TYPE.GPU);
    }

    public static List<OpenCLDevice> getOpenCLGPUs() {
        return getOpenCLDevices(Device.TYPE.GPU);
    }

    public static List<Device> getDevices(Device.TYPE type) {
        List<OpenCLDevice> oclDevs = OpenCLDevice.listDevices(type);
        List<Device> devs = new ArrayList<>();
        for (OpenCLDevice oclDev : oclDevs) {
            devs.add(oclDev);
        }
        return devs;
    }

    public static List<OpenCLDevice> getOpenCLDevices(Device.TYPE type) {
        return OpenCLDevice.listDevices(type);
    }

    public static List<OpenCLDevice> getOpenCLDevicesWithVendor(String vendor) {
        List<OpenCLPlatform> platforms = getAllOpenCLPlatforms();
        List<OpenCLDevice> devRes = new ArrayList<>();
        String v = vendor.toLowerCase();
        for (OpenCLPlatform platform : platforms) {
            if (platform.getVendor().toLowerCase().contains(v)) {
                devRes.addAll(platform.getOpenCLDevices());
            }
        }
        return devRes;
    }

    public static Device getBestDevice() {
        return KernelManager.instance().bestDevice();
    }

    /**
     * Retourne true si le Device est compatible avec le Kernel.<br>
     * Repris du code Source de Aparapi
     *
     * @param k
     *            le Kernel
     * @param d
     *            le Device
     * @return true si le kernel peu se lancer sur le device, false sinon
     */
    @SuppressWarnings("deprecation")
    public static boolean isDeviceCompatible(Kernel k, Device d) {
        if (k == null || d == null) {
            return false;
        }
        Kernel.EXECUTION_MODE mode = k.getExecutionMode();
        if (mode != Kernel.EXECUTION_MODE.AUTO) {
            switch (d.getType()) {
                case GPU:
                    return mode == Kernel.EXECUTION_MODE.GPU;
                case CPU:
                    return mode == Kernel.EXECUTION_MODE.CPU;
                case JTP:
                    return mode == Kernel.EXECUTION_MODE.JTP;
                case SEQ:
                    return mode == Kernel.EXECUTION_MODE.SEQ;
                case ACC:
                    return mode == Kernel.EXECUTION_MODE.ACC;
                default:
                    return false;
            }
        } else {
            return (d == k.getTargetDevice());
        }
    }
}
