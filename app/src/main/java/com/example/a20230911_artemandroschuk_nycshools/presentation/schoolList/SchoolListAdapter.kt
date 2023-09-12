package com.example.a20230911_artemandroschuk_nycshools.presentation.schoolList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a20230911_artemandroschuk_nycshools.R
import com.example.a20230911_artemandroschuk_nycshools.databinding.ItemSchoolBinding
import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolItem.SchoolItemEntity
import javax.inject.Inject

class SchoolListAdapter @Inject constructor() : RecyclerView.Adapter<SchoolListAdapter.SchoolListViewHolder>() {

    var schoolsList: List<SchoolItemEntity> = arrayListOf()

    var onCardClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolListViewHolder {
        val binding = ItemSchoolBinding.inflate(LayoutInflater.from(parent.context))
        return SchoolListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SchoolListViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = schoolsList.size

    inner class SchoolListViewHolder(private val binding: ItemSchoolBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.apply {
                schoolDescription.text = schoolsList[adapterPosition].overviewParagraph
                schoolName.text = schoolsList[adapterPosition].schoolName
                zipTextView.text = binding.root.context.getString(R.string.zip, schoolsList[adapterPosition].zip)
                emailTextView.text = binding.root.context.getString(R.string.email, schoolsList[adapterPosition].schoolEmail)
                phoneNumberTextView.text = binding.root.context.getString(R.string.phone_number, schoolsList[adapterPosition].phoneNumber)

                cardRoot.setOnClickListener {
                    onCardClickListener?.invoke(schoolsList[adapterPosition].dbn)
                }
            }
        }
    }
}