package app.service.impl;

import app.dto.StockClerkDto;
import app.exception.ResourceNotFoundException;
import app.model.StockClerk;
import app.repository.StockClerkRepository;
import app.service.StockClerkService;
import app.validation.StockClerkDtoValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockClerkServiceImpl implements StockClerkService {

    private final StockClerkRepository stockClerkRepository;
    private final StockClerkDtoValidator stockClerkValidator;

    @Autowired
    public StockClerkServiceImpl(
            StockClerkRepository stockClerkRepository,
            StockClerkDtoValidator stockClerkValidator) {
        this.stockClerkRepository = stockClerkRepository;
        this.stockClerkValidator = stockClerkValidator;
    }

    @Override
    public StockClerkDto findById(Long id) {
        StockClerk stockClerk = this.stockClerkRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return new StockClerkDto(stockClerk);
    }

    @Override
    public List<StockClerkDto> findAll() {
        return this.stockClerkRepository
                .findAll()
                .stream()
                .map(stockClerk -> new StockClerkDto(stockClerk))
                .collect(Collectors.toList());
    }

    @Override
    public StockClerkDto save(StockClerkDto stockClerkDto) {
        this.stockClerkValidator.validate(stockClerkDto);
        StockClerk stockClerk = this.dtoToEntity(stockClerkDto);
        return new StockClerkDto(this.stockClerkRepository.save(stockClerk));
    }

    @Override
    public void deleteById(Long id) {
        this.stockClerkRepository.deleteById(id);
    }

    private StockClerk dtoToEntity(StockClerkDto stockClerkDto) {
        StockClerk stockClerk = new StockClerk();
        BeanUtils.copyProperties(stockClerkDto, stockClerk);
        return stockClerk;
    }
}
