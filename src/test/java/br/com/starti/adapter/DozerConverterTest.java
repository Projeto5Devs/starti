package br.com.starti.adapter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import br.com.starti.domain.entity.Empresa;
import br.com.starti.domain.vo.v1.EmpresaVO;

public class DozerConverterTest {

	private Empresa mockEntity(Long numero) {

		Empresa empresa = new Empresa();
		empresa.setNomeFantasia("Nome Fantasia teste" + numero);
		empresa.setRazaoSocial("Razão Social teste" + numero);
		empresa.setSegmento("Segmento teste" + numero);
		empresa.setCnpj("Cnpj teste" + numero);


		return empresa;
	}

	private List<Empresa> mockEntityList() {

		List<Empresa> empresas = new ArrayList<>();

		for (Long i = 0l; i < 5; i++) {
			empresas.add(mockEntity(i));
		}

		return empresas;
	}

	private EmpresaVO mockEntityVO(Long numero) {

		EmpresaVO empresa = new EmpresaVO();
		empresa.setKey(numero);
		empresa.setNomeFantasia("Nome Fantasia teste" + numero);
		empresa.setRazaoSocial("Razão Social teste" + numero);
		empresa.setSegmento("Segmento teste" + numero);
		empresa.setCnpj("Cnpj teste" + numero);


		return empresa;
	}

	private List<EmpresaVO> mockEntityListVO() {

		List<EmpresaVO> empresas = new ArrayList<>();

		for (Long i = 0l; i < 5; i++) {
			empresas.add(mockEntityVO(i));
		}

		return empresas;
	}
	
	//Casos de teste
	@Test
	public void parseEntityToVOTest() {
		
		EmpresaVO output = DozerConverter.parseObject(mockEntity((long) 0), EmpresaVO.class);
		Assert.assertEquals("Nome Fantasia teste0",output.getNomeFantasia());
		Assert.assertEquals("Razão Social teste0",output.getRazaoSocial());
		Assert.assertEquals("Segmento teste0",output.getSegmento());
		Assert.assertEquals("Cnpj teste0",output.getCnpj());
	}
	
	@Test
	public void parseEntityListToVOListTest() {
		
		List<EmpresaVO> outputList = DozerConverter.parseListObject(mockEntityList(), EmpresaVO.class);
		
		EmpresaVO outputZero = outputList.get(0);
		
		Assert.assertEquals("Nome Fantasia teste0",outputZero.getNomeFantasia());
		Assert.assertEquals("Razão Social teste0",outputZero.getRazaoSocial());
		Assert.assertEquals("Segmento teste0",outputZero.getSegmento());
		Assert.assertEquals("Cnpj teste0",outputZero.getCnpj());
	}
	
	@Test
	public void parseVOToEntityTest() {
		
		Empresa output = DozerConverter.parseObject(mockEntityVO((long) 0), Empresa.class);
		Assert.assertEquals("Nome Fantasia teste0",output.getNomeFantasia());
		Assert.assertEquals("Razão Social teste0",output.getRazaoSocial());
		Assert.assertEquals("Segmento teste0",output.getSegmento());
		Assert.assertEquals("Cnpj teste0",output.getCnpj());
	}
	
	@Test
	public void parseVOListToListTest() {
		
		List<Empresa> outputList = DozerConverter.parseListObject(mockEntityListVO(), Empresa.class);
		
		Empresa outputZero = outputList.get(0);
		
		Assert.assertEquals("Nome Fantasia teste0",outputZero.getNomeFantasia());
		Assert.assertEquals("Razão Social teste0",outputZero.getRazaoSocial());
		Assert.assertEquals("Segmento teste0",outputZero.getSegmento());
		Assert.assertEquals("Cnpj teste0",outputZero.getCnpj());
	}
}
