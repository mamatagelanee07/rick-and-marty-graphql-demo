package com.andigeeky.rickandmorty.characters.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.andigeeky.rickandmorty.R
import com.andigeeky.rickandmorty.characters.model.Character

class CharactersAdapter(val onItemClick: (characterId: Character) -> Unit) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {
    private val characters = mutableListOf<Character>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        characters[position].url?.let {
            holder.image.load(it)
        }
        holder.title.text = characters[position].name.orEmpty()
        holder.subtitle.text = characters[position].gender.key
        holder.itemView.setOnClickListener {
            onItemClick(characters[position])
        }
    }

    override fun getItemCount() = characters.size

    fun submitList(items: List<Character>) {
        characters.clear()
        characters.addAll(items)
        notifyDataSetChanged()
    }

    class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.item_image_character)
        var title: TextView = view.findViewById(R.id.item_title_character)
        var subtitle: TextView = view.findViewById(R.id.item_subtitle_character)
    }
}