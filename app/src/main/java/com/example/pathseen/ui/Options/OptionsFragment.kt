package com.example.pathseen.ui.Options

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pathseen.databinding.FragmentOptionsBinding
import com.example.pathseen.ui.signin.SignInActivity

class OptionsFragment : Fragment() {

private var _binding: FragmentOptionsBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val optionsViewModel = ViewModelProvider(this).get(OptionsViewModel::class.java)

      _binding = FragmentOptionsBinding.inflate(inflater, container, false)
      val root: View = binding.root

      optionsViewModel.errorMsg.observe(viewLifecycleOwner){errorMsg->
          Toast.makeText(requireActivity(), errorMsg, Toast.LENGTH_LONG).show()
      }

      optionsViewModel.userLoaded.observe(viewLifecycleOwner){user->
          with(binding){
              uinfoTextView.text=user?.name
              uemailTextView.text=user?.email
              ugenresTextView.text=user?.genres
          }
      }

      optionsViewModel.loadUserInfo()

      binding.buttonSignOut.setOnClickListener{
          optionsViewModel.signOut()
          val intent=Intent(activity, SignInActivity::class.java)
          startActivity(intent)
          activity?.finish()
      }

    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}