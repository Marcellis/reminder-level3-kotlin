package nl.hva.level3example.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.reminder_item.view.*
import nl.hva.level3example.R
import nl.hva.level3example.model.Reminder

class ReminderAdapter(
    private val reminders: ArrayList<Reminder>
) : RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>()  {

    inner class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(reminder: Reminder) {
            itemView.reminderText.text = reminder.reminderText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reminder_item, parent, false)

        return ReminderViewHolder(view)
    }

    override fun getItemCount(): Int = reminders.size

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        holder.bind(reminders[position])
    }


}