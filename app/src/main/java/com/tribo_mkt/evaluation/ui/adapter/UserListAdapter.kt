package com.tribo_mkt.evaluation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tribo_mkt.evaluation.R
import com.tribo_mkt.evaluation.databinding.UserListItemBinding
import com.tribo_mkt.evaluation.domain.User
import com.tribo_mkt.evaluation.ui.adapter.UserListAdapter.UserListViewHolder
import java.util.*

//class UserListAdapter(val clickListener: UserClickListener) :
class UserListAdapter() :
    ListAdapter<User, UserListViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    class UserListViewHolder(private var binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User, position: Int) {

            binding.user = user

            binding.apply {
                letra.text = user.name.substring(0, 2).uppercase(Locale.getDefault())
                if ((position - 1) % 2 == 0) {
                    binding.fundo.setBackgroundColor(ContextCompat.getColor(fundo.context, R.color.fundo))
                }
            }

//            binding.albunsBotao.setOnClickListener {
//                val intent = Intent(activity, AlbunsActivity::class.java)
//                intent.putExtra("usuarioId", items[position].id)
//                intent.putExtra("usuarioNome", items[position].usuarioNome)
//                activity.startActivity(intent)
//            }
//            binding.postagensBotao.setOnClickListener {
//                val intent = Intent(activity, PostagensActivity::class.java)
//                intent.putExtra("usuarioId", items[position].id)
//                intent.putExtra("usuarioNome", items[position].usuarioNome)
//                activity.startActivity(intent)
//            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): UserListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = UserListItemBinding.inflate(layoutInflater, parent, false)
                return UserListViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        return UserListViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}

class UserClickListener(val clickListener: (chapter: User) -> Unit) {
    fun onClick(chapter: User) = clickListener(chapter)
}

