package com.igeek.university.service.impl;

import com.igeek.university.dao.MedicationReminderRepository;
import com.igeek.university.entity.MedicationReminder;
import com.igeek.university.service.MedicationReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationReminderServiceImpl implements MedicationReminderService {

    @Autowired
    private MedicationReminderRepository medicationReminderRepository;

    @Override
    public List<MedicationReminder> getAllReminders() {
        return medicationReminderRepository.findAll();
    }

    @Override
    public List<MedicationReminder> getRemindersByUserID(Long userID) {
        return medicationReminderRepository.findByUserID(userID);
    }

    @Override
    public MedicationReminder addReminder(MedicationReminder reminder) {
        return medicationReminderRepository.save(reminder);
    }

    @Override
    public void updateReminder(Long reminderID, MedicationReminder reminder) {
        // Check if reminder with reminderID exists
        MedicationReminder existingReminder = medicationReminderRepository.findById(reminderID)
                .orElseThrow(() -> new RuntimeException("Reminder not found with id: " + reminderID));

        // Update fields
        existingReminder.setUserID(reminder.getUserID());
        existingReminder.setReminderTime(reminder.getReminderTime());
        existingReminder.setMedicationName(reminder.getMedicationName());
        existingReminder.setDosage(reminder.getDosage());

        medicationReminderRepository.save(existingReminder);
    }

    @Override
    public void deleteReminder(Long reminderID) {
        medicationReminderRepository.deleteById(reminderID);
    }
}
