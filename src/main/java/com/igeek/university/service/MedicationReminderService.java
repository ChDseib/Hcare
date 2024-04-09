package com.igeek.university.service;

import com.igeek.university.entity.MedicationReminder;

import java.util.List;

public interface MedicationReminderService {
    List<MedicationReminder> getAllReminders();
    List<MedicationReminder> getRemindersByUserID(Long userID);
    MedicationReminder addReminder(MedicationReminder reminder);
    void updateReminder(Long reminderID, MedicationReminder reminder);
    void deleteReminder(Long reminderID);
}
