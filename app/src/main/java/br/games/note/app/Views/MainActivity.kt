package br.games.note.app.Views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.games.note.app.Adapters.ViewPagerAdapter
import br.games.note.app.Views.Empresa.EditEmpresasActivity
import br.games.note.app.Views.Empresa.EmpresasFragment
import br.games.note.app.Views.JogoFavorito.EditJogosActivity
import br.games.note.app.Views.JogoFavorito.JogosFragment
import br.games.note.app.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarTabLayout()

        binding.actionButton.setOnClickListener{
            binding.LinearLayoutMain.visibility = View.GONE
            binding.confirmarEmpresaButton.visibility = View.VISIBLE
            binding.confirmarJogoButton.visibility = View.VISIBLE
        }

        binding.confirmarJogoButton.setOnClickListener{
            binding.confirmarEmpresaButton.visibility = View.GONE
            binding.confirmarJogoButton.visibility = View.GONE
            val intent = Intent(this, EditJogosActivity::class.java)
            binding.LinearLayoutMain.visibility = View.VISIBLE
            startActivity(intent)
        }

        binding.confirmarEmpresaButton.setOnClickListener{
            binding.confirmarEmpresaButton.visibility = View.GONE
            binding.confirmarJogoButton.visibility = View.GONE
            val intent = Intent(this, EditEmpresasActivity::class.java)
            binding.LinearLayoutMain.visibility = View.VISIBLE
            startActivity(intent)
        }
    }

    private fun configurarTabLayout(){

        val adapter = ViewPagerAdapter(fragmentActivity = this)
        binding.viewPager.adapter = adapter

        adapter.addFragment(EmpresasFragment(), "Empresas")
        adapter.addFragment(JogosFragment(), "Jogos")

        binding.viewPager.offscreenPageLimit = adapter.itemCount

        var mediator = TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = adapter.getTitle(position)
        }
        mediator.attach()
    }
}