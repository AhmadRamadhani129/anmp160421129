package com.example.anmpweek1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.anmpweek1.databinding.FragmentGameBinding
import com.google.android.material.color.utilities.Score
import kotlin.random.Random

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private var num1: Int = 0
    private var num2: Int = 0
    public var score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun RandomQuestion()
    {
        num1 = Random.nextInt(101)
        num2 = Random.nextInt(101)
    }

    private fun DisplayQuestion(){
        binding.txtNum.text = "$num1 + $num2"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            binding.txtTurn.text = "$playerName's Turn"
        }

        RandomQuestion()
        DisplayQuestion()

        binding.btnSubmit.setOnClickListener {
            val answer = binding.editTextAnswer.text.toString()
            if(answer == (num1 + num2).toString()){
                score += 1
                RandomQuestion()
                DisplayQuestion()
                binding.editTextAnswer.setText("")
            }else{
                val action = GameFragmentDirections.actionResultFragment(score)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

}