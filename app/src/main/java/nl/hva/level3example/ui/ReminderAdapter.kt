package nl.hva.level3example.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nl.hva.level3example.model.Reminder
import nl.hva.level3example.databinding.ReminderItemBinding

class ReminderAdapter(
    private val reminders: ArrayList<Reminder>
) : RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>()  {

    inner class ReminderViewHolder(
        private val itemBinding: ReminderItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(reminder: Reminder) {
            itemBinding.reminderText.text = reminder.reminderText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val itemBinding = ReminderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ReminderViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = reminders.size

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        holder.bind(reminders[position])
    }


}