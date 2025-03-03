package com.devsu.accountservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.devsu.accountservice.enums.TipoCuenta;
import com.devsu.accountservice.model.Cuenta;
import com.devsu.accountservice.repository.CuentaRepository;

@Component
public class DataLoader implements CommandLineRunner{
	@Autowired
	private CuentaRepository cuentaRepository;

	@Override
	public void run(String... args) throws Exception {
		if(cuentaRepository.count() == 0) {
			Cuenta cu1 = new Cuenta("478758", TipoCuenta.Ahorros, 2000, true, 1L);
			Cuenta cu2 = new Cuenta("225487", TipoCuenta.Corriente, 100, true, 2L);
			Cuenta cu3 = new Cuenta("496825", TipoCuenta.Ahorros, 540, true, 2L);
			cuentaRepository.save(cu1);
			cuentaRepository.save(cu2);
			cuentaRepository.save(cu3);
			
			System.out.println("Cuentas predefinidas creadas... ");
		}else {
			System.out.println("Ya existen Cuentas predefinidas");
		}
	}
}