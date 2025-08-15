// utils/maintenance.ts

import { Equipment, MaintenanceFrequencyUnit } from "../types";
import { isValid } from 'date-fns';

// Eliminar toda la función calculateMaintenanceDetails y sus logs

// Mantener solo la función de transformación si es necesaria para compatibilidad:
export const transformApiDataToEquipment = (apiData: any): Equipment => {
    let maintenanceFrequency;
    if (apiData.maintenanceFrequency && typeof apiData.maintenanceFrequency === 'object') {
        maintenanceFrequency = apiData.maintenanceFrequency;
    } else {
        maintenanceFrequency = {
            value: apiData.maintenanceFrequencyValue,
            unit: apiData.maintenanceFrequencyUnit,
        };
    }
    return {
        ...apiData,
        maintenanceFrequency,
    };
};